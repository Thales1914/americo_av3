package com.seuprojeto.hotel.hotel_api.service;

import com.seuprojeto.hotel.hotel_api.model.Funcionario;
import com.seuprojeto.hotel.hotel_api.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public String inserir(Funcionario f) {
        if (f.getIdade() < 16)
            return "Erro: Funcionários devem ter no mínimo 16 anos.";

        if (repository.existsById(f.getCpf()))
            return "Erro: Já existe um funcionário com este CPF.";

        repository.save(f);
        return "Funcionário inserido com sucesso!";
    }

    public List<Funcionario> listar() {
        return repository.findAll();
    }

    public Funcionario consultarObjeto(String cpf) {
        return repository.findById(cpf).orElse(null);
    }

    public String atualizar(String cpf, Funcionario dados) {
        if (!repository.existsById(cpf))
            return "Erro: Funcionário não encontrado.";

        dados.setCpf(cpf);
        repository.save(dados);
        return "Funcionário atualizado com sucesso!";
    }

    public String excluir(String cpf) {
        if (!repository.existsById(cpf))
            return "Erro: Funcionário não encontrado.";

        repository.deleteById(cpf);
        return "Funcionário excluído com sucesso!";
    }
}
