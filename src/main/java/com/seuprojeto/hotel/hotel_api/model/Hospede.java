package com.seuprojeto.hotel.hotel_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hospede")
public class Hospede extends Pessoa {

    private String rg;
    private boolean fidelidade;

    public Hospede() {}

    public Hospede(String cpf, String nome, int idade, String rg, boolean fidelidade) {
        super(cpf, nome, idade);
        this.rg = rg;
        this.fidelidade = fidelidade;
    }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public boolean isFidelidade() { return fidelidade; }
    public void setFidelidade(boolean fidelidade) { this.fidelidade = fidelidade; }

    @Override
    public String toString() {
        return String.format(
                "Hospede{cpf='%s', nome='%s', idade=%d, rg='%s', fidelidade=%s}",
                cpf, nome, idade, rg, fidelidade
        );
    }
}
