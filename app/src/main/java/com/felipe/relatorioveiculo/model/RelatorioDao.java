package com.felipe.relatorioveiculo.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class RelatorioDao {

    private ArrayList<Relatorio> bancoDeDados;

    public ArrayList<Relatorio> obterLista(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults lista = realm.where(Relatorio.class).findAll();
        bancoDeDados.clear();
        bancoDeDados.addAll(realm.copyFromRealm(lista));
        return bancoDeDados;
    }

    public void adicionarNaLista(Relatorio r){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyFromRealm(r);
        realm.commitTransaction();
    }

    public int atualizaNaLista(Relatorio r){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(r);
        realm.commitTransaction();


        for(int i = 0; i < bancoDeDados.size(); i++){
            if(bancoDeDados.get(i).getId().equals(r.getId())){
                bancoDeDados.set(i, r);
                return i;
            }
        }
        return -1; //nao encontrou o compromisso para atualizar
    }

    public int excluiDaLista(Relatorio r){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(Relatorio.class).equalTo("id", r.getId()).findFirst().deleteFromRealm();
        realm.commitTransaction();

        for(int i = 0; i < bancoDeDados.size(); i++){
            if(bancoDeDados.get(i).getId().equals(r.getId())){
                bancoDeDados.remove(i);
                return i;
            }
        }
        return -1;//nao encontrou o compromisso para excluir
    }

    public Relatorio obterObjetoPeloId(String id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Relatorio r = realm.copyFromRealm(realm.where(Relatorio.class).equalTo("id", id).findFirst());
        realm.commitTransaction();
        return r;
    }



    //Padrão de projeto DAO
    private static RelatorioDao INSTANCIA;

    public static RelatorioDao obterInstancia(){
        if (INSTANCIA == null){
            INSTANCIA = new RelatorioDao();
        }
        return INSTANCIA;
    }

    private RelatorioDao(){
        bancoDeDados = new ArrayList<Relatorio>();

    }
}
