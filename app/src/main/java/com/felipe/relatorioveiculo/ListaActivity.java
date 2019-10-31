package com.felipe.relatorioveiculo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.felipe.relatorioveiculo.adapter.RelatorioAdapter;
import com.felipe.relatorioveiculo.model.Relatorio;
import com.felipe.relatorioveiculo.model.RelatorioDao;

public class ListaActivity extends AppCompatActivity {

    private RelatorioAdapter relatorioAdapter;
    private RecyclerView listaRelatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaRelatorio = findViewById(R.id.rvRelatorio);
        relatorioAdapter = new RelatorioAdapter();
        listaRelatorio.setLayoutManager( new LinearLayoutManager(this));
        listaRelatorio.setAdapter(relatorioAdapter);
    }

    public void modificarRelatorio(View v, String idDoCompromisso){
        Intent intencao = new Intent( this, CadastroActivity.class );
        intencao.putExtra("idCompromisso", idDoCompromisso);
        startActivityForResult(intencao, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            //significa que estava voltando da tela do formulário
            if (resultCode == 200){
                //atualizou um item
                int posicao = data.getIntExtra("posicaoDoObjetoEditado", -1);
                relatorioAdapter.notifyItemChanged( posicao );
                listaRelatorio.smoothScrollToPosition(posicao);
            }else if (resultCode == 201){
                //adicionou um item
                Toast.makeText(this, "Compromisso inserido com sucesso", Toast.LENGTH_LONG).show();
                int posicao = RelatorioDao.obterInstancia().obterLista().size()-1;
                relatorioAdapter.notifyItemInserted( posicao );
                listaRelatorio.smoothScrollToPosition(posicao);
            }else if (resultCode == 202){
                //excluir um item
                Toast.makeText(this, "Compromisso excluído com sucesso", Toast.LENGTH_LONG).show();
                int posicao = data.getIntExtra("posicaoDoObjetoExcluido", -1);
                relatorioAdapter.notifyItemRemoved(posicao);
            }
        }
    }

    public void adicionarCompromisso(View v){
        Intent intencao = new Intent( this, CadastroActivity.class );
        startActivityForResult(intencao, 1);
    }
}
