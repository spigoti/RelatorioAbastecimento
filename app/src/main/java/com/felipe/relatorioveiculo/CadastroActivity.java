package com.felipe.relatorioveiculo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;

import com.felipe.relatorioveiculo.model.Relatorio;
import com.felipe.relatorioveiculo.model.RelatorioDao;
import com.google.android.material.textfield.TextInputEditText;


import java.text.DateFormat;
import java.util.Calendar;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroActivity extends AppCompatActivity {

    private Relatorio objetoRelatorio;
    private String idRelatorio;
    private TextInputEditText kmAtual;
    private TextInputEditText litros;
    private TextInputEditText textData;
    private MaterialSpinner spinnerPosto;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        spinnerPosto = findViewById(R.id.spinnerPosto);
        kmAtual = findViewById(R.id.inputKm);
        litros = findViewById(R.id.inputLitros);
        textData = findViewById(R.id.textData);
        textData.setKeyListener(null);//desabilitando teclado na entrada de data...
//
//
////        //populando o spinner com opções
////        String[] opcoesPosto = getResources().getStringArray(R.array.opcoes_postos); //obtendo a partir do XML
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoesPosto);
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        spinnerPosto.setAdapter(adapter);
//
//        idRelatorio = getIntent().getStringExtra("idCompromisso");
//        if(idRelatorio == null){
//            objetoRelatorio = new Relatorio();
//
//            //preparando a tela para criação de compromisso
//            Button btExcluir = findViewById(R.id.btExcluir);
//            btExcluir.setVisibility( View.INVISIBLE );
//        }else{
//            objetoRelatorio = RelatorioDao.obterInstancia().obterObjetoPeloId(idRelatorio);
//
//            //preparando a tela para edição de compromisso
//            //populando os elementos com o compromisso atual
//            litros.setText(objetoRelatorio.getLitros().toString());
//            for(int i = 0; i < spinnerPosto.getAdapter().getCount(); i++){
//                if (spinnerPosto.getAdapter().getItem(i).toString() == objetoRelatorio.getPosto()){
//                    spinnerPosto.setSelection(i+1);
//                    break;
//                }
//            }
//            DateFormat formatador = android.text.format.DateFormat.getDateFormat( getApplicationContext() );
//            String dataSelecionadaFormatada = formatador.format( objetoRelatorio.getData().getTime() );
//            textData.setText( dataSelecionadaFormatada );
//        }

    }

    public void salvar(View v){
//        objetoRelatorio.setPosto(etDescricao.getText().toString());
//        objetoRelatorio.setPosto( spinnerPosto.getSelectedItem().toString());
        if(idRelatorio == null) {
            //está salvando
            RelatorioDao.obterInstancia().adicionarNaLista(objetoRelatorio);
            setResult(201);
        }else{
            //está editando
            int posicaoDoObjeto = RelatorioDao.obterInstancia().atualizaNaLista(objetoRelatorio);
            Intent intencaoDeFechamentoDaActivityFormulario = new Intent();
            intencaoDeFechamentoDaActivityFormulario.putExtra("posicaoDoObjetoEditado", posicaoDoObjeto);
            setResult(200, intencaoDeFechamentoDaActivityFormulario);
        }
        finish();

    }

    public void selecionarData(View v){
        Calendar dataPadrao = objetoRelatorio.getData();;
        if(dataPadrao == null){
            dataPadrao = Calendar.getInstance();
        }

        DatePickerDialog dialogoParaPegarData = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar dataSelecionada = Calendar.getInstance();
                        dataSelecionada.set(year,month,dayOfMonth);
                        objetoRelatorio.setData(dataSelecionada);

                        DateFormat formatador = android.text.format.DateFormat.getDateFormat( getApplicationContext() );
                        String dataSelecionadaFormatada = formatador.format( dataSelecionada.getTime() );
                        textData.setText( dataSelecionadaFormatada );
                    }
                },
                dataPadrao.get(Calendar.YEAR),
                dataPadrao.get(Calendar.MONTH),
                dataPadrao.get(Calendar.DAY_OF_MONTH)
        );
        dialogoParaPegarData.show();
    }

    public void excluir(View v){
        new AlertDialog.Builder(this)
                .setTitle("Você tem certeza?")
                .setMessage("Você quer mesmo excluir?")
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int posicaoDoObjeto = RelatorioDao.obterInstancia().excluiDaLista(objetoRelatorio);
                        Intent intencaoDeFechamentoDaActivityFormulario = new Intent();
                        intencaoDeFechamentoDaActivityFormulario.putExtra("posicaoDoObjetoExcluido", posicaoDoObjeto);
                        setResult(202, intencaoDeFechamentoDaActivityFormulario);
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
