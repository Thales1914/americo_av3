package com.seuprojeto.hotel.hotel_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa {

    private String funcao;

    public Funcionario() {}

    public Funcionario(String cpf, String nome, int idade, String funcao) {
        super(cpf, nome, idade);
        this.funcao = funcao;
    }

    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }

    @Override
    public String toString() {
        return String.format(
                "Funcionario{cpf='%s', nome='%s', idade=%d, funcao='%s'}",
                cpf, nome, idade, funcao
        );
    }
}
