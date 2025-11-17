package com.seuprojeto.hotel.hotel_api.service;

import com.seuprojeto.hotel.hotel_api.model.Hospede;
import com.seuprojeto.hotel.hotel_api.repository.HospedeRepository;
import org.springframework.stereotype.Service; // Importar
import java.sql.SQLException;
import java.util.List;

@Service // ADICIONADO: Spring gerencia esta classe
public class HospedeService {

    // Ajuste para usar injeção de dependência se for usar o Spring Data,
    // mas mantido para JDBC puro por simplicidade:
    private final HospedeRepository repository = new HospedeRepository();

    public String inserir(Hospede novoHospede) {
        if (novoHospede.getIdade() < 18) {
            return "Erro: Hóspedes devem ser maiores de 18 anos.";
        }

        try {
            if (repository.findByCpf(novoHospede.getCpf()) != null) {
                return "Erro: Já existe um hóspede com este CPF.";
            }
            repository.inserir(novoHospede);
            return "Hóspede inserido com sucesso!";
        } catch (SQLException e) {
            return "Erro ao acessar o banco de dados: " + e.getMessage();
        }
    }

    public List<Hospede> listar() {
        try {
            return repository.findAll();
        } catch (SQLException e) {
            System.err.println("Erro ao listar hóspedes: " + e.getMessage());
            return List.of();
        }
    }

    public Hospede consultarObjeto(String cpf) {
        try {
            return repository.findByCpf(cpf);
        } catch (SQLException e) {
            System.err.println("Erro ao consultar hóspede (Objeto): " + e.getMessage());
            return null;
        }
    }

    public String atualizar(String cpf, Hospede dadosAtualizados) {
        try {
            dadosAtualizados.setCpf(cpf);
            if (repository.atualizar(dadosAtualizados)) {
                return "Hóspede atualizado com sucesso!";
            } else {
                return "Erro: Hóspede com CPF " + cpf + " não encontrado para atualização.";
            }
        } catch (SQLException e) {
            return "Erro ao atualizar hóspede: " + e.getMessage();
        }
    }

    public String excluir(String cpf) {
        try {
            if (repository.excluir(cpf)) {
                return "Hóspede excluído com sucesso!";
            } else {
                return "Erro: Hóspede com CPF " + cpf + " não encontrado para exclusão.";
            }
        } catch (SQLException e) {
            return "Erro ao excluir hóspede: " + e.getMessage();
        }
    }
}