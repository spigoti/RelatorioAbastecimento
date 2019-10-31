package com.felipe.relatorioveiculo.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Relatorio extends RealmObject {

    @PrimaryKey
    private String id;
    private Double litros;
    private String posto;
    private Double kmAtual;
    private Date dataPura;

    @Ignore
    private Calendar data;

    public Relatorio(){
        id = UUID.randomUUID().toString();
    }

    public String getId(){
        return id;
    }

    public Double getLitros() {
        return litros;
    }

    public void setLitros(Double litros) {
        this.litros = litros;
    }

    public Double getKmAtual() {
            return kmAtual;
    }

    public void setKmAtual(Double kmAtual) {
        this.kmAtual = kmAtual;
    }

    public Calendar getData() {
        if (dataPura != null){

            data = Calendar.getInstance();
            data.setTime(dataPura);
        }
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;

        this.dataPura = data.getTime();
    }

    public String getPosto(){
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }
}
