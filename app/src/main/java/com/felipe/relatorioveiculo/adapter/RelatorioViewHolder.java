package com.felipe.relatorioveiculo.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.felipe.relatorioveiculo.ListaActivity;
import com.felipe.relatorioveiculo.R;
import com.felipe.relatorioveiculo.model.Relatorio;

import java.text.DateFormat;

public class RelatorioViewHolder extends RecyclerView.ViewHolder {
    private TextView tvDescricao;
    private TextView tvData;
    private ConstraintLayout clPai;
    private String idDoCompromisso;


    public RelatorioViewHolder(@NonNull View itemView) {
        super(itemView);

        //adicionando evento de clique no elemento principal da gaveta
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cast do contexto para activity atual e chamada do método
                ((ListaActivity) v.getContext()).modificarCompromisso(v, idDoCompromisso);
            }
        });


//        tvDescricao = itemView.findViewById(R.id.tvDescricao);
        tvData = itemView.findViewById(R.id.tvData);
        clPai = (ConstraintLayout) itemView;

    }

    public void atualizaGavetaComOObjetoQueChegou(Relatorio r){
        //armazenando a posição do objeto na lista, para usar caso o método modificarCompromisso seja chamado
        idDoCompromisso = r.getId();

        tvDescricao.setText( r.getPosto() );

        DateFormat formatador = android.text.format.DateFormat.getDateFormat( tvDescricao.getContext() );
        String dataFormatada = formatador.format( r.getData().getTime() );
        tvData.setText( dataFormatada );

        switch (r.getPosto()){
            case "ipiranga":
//                clPai.setBackgroundColor( clPai.getResources().getColor(R.color.corCompromissoImportante, null) );
                break;
            case "petrobras":
//                clPai.setBackgroundColor( clPai.getResources().getColor(R.color.corCompromissoMedio, null) );
                break;
            case "texaco":
//                clPai.setBackgroundColor( clPai.getResources().getColor(R.color.corCompromissoMedio, null) );
                break;
            case "shell":
//                clPai.setBackgroundColor( clPai.getResources().getColor(R.color.corCompromissoMedio, null) );
                break;
            case "outros":
//                clPai.setBackgroundColor( clPai.getResources().getColor(R.color.corCompromissoMedio, null) );
                break;
            default:
//                clPai.setBackgroundColor( clPai.getResources().getColor(R.color.corCompromissoNaoImportante, null) );
        }

    }
}
