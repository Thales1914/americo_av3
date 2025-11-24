package com.seuprojeto.hotel.hotel_api.service;

import com.seuprojeto.hotel.hotel_api.dto.HospedeDTO;
import com.seuprojeto.hotel.hotel_api.model.Hospede;
import com.seuprojeto.hotel.hotel_api.repository.HospedeRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class HospedeService {

    private final HospedeRepository repository = new HospedeRepository();

    public Hospede fromDTO(HospedeDTO dto) {
        Hospede h = new Hospede();
        h.setCpf(dto.getCpf());
        h.setNome(dto.getNome());
        h.setIdade(dto.getIdade());
        h.setRg(dto.getRg());
        h.setFidelidade(dto.getClienteFidelidade());
        return h;
    }

    public String inserir(HospedeDTO dto) {
        Hospede novoHospede = fromDTO(dto);

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
            return List.of();
        }
    }

    public Hospede consultarObjeto(String cpf) {
        try {
            return repository.findByCpf(cpf);
        } catch (SQLException e) {
            return null;
        }
    }

    public String atualizar(String cpf, HospedeDTO dto) {
        try {
            Hospede atualizado = fromDTO(dto);
            atualizado.setCpf(cpf);

            if (repository.atualizar(atualizado)) {
                return "Hóspede atualizado com sucesso!";
            } else {
                return "Erro: Hóspede não encontrado.";
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
                return "Erro: Hóspede não encontrado.";
            }

        } catch (SQLException e) {
            return "Erro ao excluir hóspede: " + e.getMessage();
        }
    }
}
