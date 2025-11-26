package com.seuprojeto.hotel.hotel_api.repository;

import com.seuprojeto.hotel.hotel_api.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospedeRepository extends JpaRepository<Hospede, String> {
}
