package com.fife.fipe_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataMarca(    
    String codigo,
    String nome) {

        @Override
        public final String toString() {
            return "codigo= " + codigo + ", nome= " + nome;
        }
}
