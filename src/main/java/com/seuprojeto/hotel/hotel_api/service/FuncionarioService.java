package com.seuprojeto.hotel.hotel_api.service;

import com.seuprojeto.hotel.hotel_api.model.Funcionario;
import com.seuprojeto.hotel.hotel_api.repository.FuncionarioRepository;
import org.springframework.stereotype.Service; // Importar
import java.sql.SQLException;
import java.util.List;

@Service // ADICIONADO: Spring gerencia esta classe
public class FuncionarioService {

    private final FuncionarioRepository repository = new FuncionarioRepository();

    public String inserir(Funcionario novoFuncionario) {
        if (novoFuncionario.getIdade() < 16) {
            return "Erro: Funcionários devem ter no mínimo 16 anos.";
        }

        try {
            if (repository.findByCpf(novoFuncionario.getCpf()) != null) {
                return "Erro: Já existe um funcionário com este CPF.";
            }
            repository.inserir(novoFuncionario);
            return "Funcionário inserido com sucesso!";
        } catch (SQLException e) {
            return "Erro ao acessar o banco de dados: " + e.getMessage();
        }
    }

    public List<Funcionario> listar() {
        try {
            return repository.findAll();
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
            return List.of();
        }
    }

    public Funcionario consultarObjeto(String cpf) {
        try {
            return repository.findByCpf(cpf);
        } catch (SQLException e) {
            System.err.println("Erro ao consultar funcionário (Objeto): " + e.getMessage());
            return null;
        }
    }

    public String atualizar(String cpf, Funcionario dadosAtualizados) {
        try {
            dadosAtualizados.setCpf(cpf);
            if (repository.atualizar(dadosAtualizados)) {
                return "Funcionário atualizado com sucesso!";
            } else {
                return "Erro: Funcionário com CPF " + cpf + " não encontrado para atualização.";
            }
        } catch (SQLException e) {
            return "Erro ao atualizar funcionário: " + e.getMessage();
        }
    }

    public String excluir(String cpf) {
        try {
            if (repository.excluir(cpf)) {
                return "Funcionário excluído com sucesso!";
            } else {
                return "Erro: Funcionário com CPF " + cpf + " não encontrado para exclusão.";
            }
        } catch (SQLException e) {
            return "Erro ao excluir funcionário: " + e.getMessage();
        }
    }
}