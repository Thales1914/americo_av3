package com.seuprojeto.hotel.hotel_api.service;

import com.seuprojeto.hotel.hotel_api.model.Hospede;
import com.seuprojeto.hotel.hotel_api.repository.HospedeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospedeService {

    private final HospedeRepository repository;

    public HospedeService(HospedeRepository repository) {
        this.repository = repository;
    }

    public String inserir(Hospede h) {
        if (repository.existsById(h.getCpf())) {
            return "Erro: Já existe um hóspede com este CPF.";
        }

        repository.save(h);
        return "Hóspede inserido com sucesso!";
    }

    public List<Hospede> listar() {
        return repository.findAll();
    }

    public Hospede consultarObjeto(String cpf) {
        return repository.findById(cpf).orElse(null);
    }

    public String atualizar(String cpf, Hospede dados) {
        if (!repository.existsById(cpf))
            return "Erro: Hóspede não encontrado.";

        dados.setCpf(cpf);
        repository.save(dados);
        return "Hóspede atualizado com sucesso!";
    }

    public String excluir(String cpf) {
        if (!repository.existsById(cpf))
            return "Erro: Hóspede não encontrado.";

        repository.deleteById(cpf);
        return "Hóspede excluído com sucesso!";
    }
}
