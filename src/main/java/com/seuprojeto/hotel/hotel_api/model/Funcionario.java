package com.seuprojeto.hotel.hotel_api.model;

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
        return String.format("CPF: %s, Nome: %s, Idade: %d, Função: %s",
                getCpf(), getNome(), getIdade(), funcao);
    }
}
