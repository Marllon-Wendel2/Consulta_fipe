package com.fife.fipe_app.model;


public record DataAnos(String codigo, String nome) {

    @Override
    public final String toString() {
        return "codigo= " + codigo + ", nome= " + nome;
    }
}