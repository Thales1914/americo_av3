package com.seuprojeto.hotel.hotel_api.service;

import com.seuprojeto.hotel.hotel_api.dto.FuncionarioDTO;
import com.seuprojeto.hotel.hotel_api.model.Funcionario;
import com.seuprojeto.hotel.hotel_api.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository = new FuncionarioRepository();

    public Funcionario fromDTO(FuncionarioDTO dto) {
        Funcionario f = new Funcionario();
        f.setCpf(dto.getCpf());
        f.setNome(dto.getNome());
        f.setIdade(dto.getIdade());
        f.setFuncao(dto.getCargo());
        return f;
    }

    public String inserir(FuncionarioDTO dto) {
        Funcionario novoFuncionario = fromDTO(dto);

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
            return List.of();
        }
    }

    public Funcionario consultarObjeto(String cpf) {
        try {
            return repository.findByCpf(cpf);
        } catch (SQLException e) {
            return null;
        }
    }

    public String atualizar(String cpf, FuncionarioDTO dto) {
        try {
            Funcionario atualizado = fromDTO(dto);
            atualizado.setCpf(cpf);

            if (repository.atualizar(atualizado)) {
                return "Funcionário atualizado com sucesso!";
            } else {
                return "Erro: Funcionário não encontrado.";
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
                return "Erro: Funcionário não encontrado.";
            }
        } catch (SQLException e) {
            return "Erro ao excluir funcionário: " + e.getMessage();
        }
    }
}
