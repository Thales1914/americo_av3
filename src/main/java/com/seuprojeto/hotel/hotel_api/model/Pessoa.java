package com.seuprojeto.hotel.hotel_api.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Pessoa {

    @Id
    @Column(nullable = false, unique = true)
    protected String cpf;

    protected String nome;
    protected int idade;

    public Pessoa() {}

    public Pessoa(String cpf, String nome, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    @Override
    public abstract String toString();
}
