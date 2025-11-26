package com.seuprojeto.hotel.hotel_api.repository;

import com.seuprojeto.hotel.hotel_api.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
}
