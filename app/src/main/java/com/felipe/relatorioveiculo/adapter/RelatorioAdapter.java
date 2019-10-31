package com.felipe.relatorioveiculo.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.felipe.relatorioveiculo.R;
import com.felipe.relatorioveiculo.model.Relatorio;
import com.felipe.relatorioveiculo.model.RelatorioDao;

public class RelatorioAdapter extends RecyclerView.Adapter{

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //1: inflar XML
        ConstraintLayout elementoPrincipalXML = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_relatorio, parent, false
        );

        //2: associar os objetos inflados a um novo view holder
        RelatorioViewHolder gaveta = new RelatorioViewHolder(elementoPrincipalXML);

        //3: retornar o view holder que foi criado no passo 2

        Log.d("AULA", "Gaveta criada: "+gaveta);

        return gaveta;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Relatorio c = RelatorioDao.obterInstancia().obterLista().get(position);
        RelatorioViewHolder gaveta = (RelatorioViewHolder) holder;

        gaveta.atualizaGavetaComOObjetoQueChegou(c);
        Log.d("AULA", "Atualizou com o item na posição "+position+" a gaveta : "+gaveta);

    }

    @Override
    public int getItemCount() {
        return RelatorioDao.obterInstancia().obterLista().size();
    }
}
