package com.seuprojeto.hotel.hotel_api.dto;

public class HospedeDTO extends PessoaDTO {

    private boolean clienteFidelidade;

    public boolean isClienteFidelidade() {
        return clienteFidelidade;
    }

    public boolean getClienteFidelidade() {  // <-- ADICIONE ESTE MÉTODO
        return clienteFidelidade;
    }

    public void setClienteFidelidade(boolean clienteFidelidade) {
        this.clienteFidelidade = clienteFidelidade;
    }
}
