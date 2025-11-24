package com.seuprojeto.hotel.hotel_api.dto;

public class FuncionarioDTO extends PessoaDTO {
    private String cargo;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
