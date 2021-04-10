package com.sdainfo.minhastarefas.act.model;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private long id;
    private String nome_tarefa;

    public Tarefa() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome_tarefa() {
        return nome_tarefa;
    }

    public void setNome_tarefa(String nome_tarefa) {
        this.nome_tarefa = nome_tarefa;
    }
}
