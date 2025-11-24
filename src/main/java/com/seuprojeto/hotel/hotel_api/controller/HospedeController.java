package com.seuprojeto.hotel.hotel_api.controller;

import com.seuprojeto.hotel.hotel_api.dto.HospedeDTO;
import com.seuprojeto.hotel.hotel_api.model.Hospede;
import com.seuprojeto.hotel.hotel_api.model.ApiResponse;
import com.seuprojeto.hotel.hotel_api.service.HospedeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeController {

    private final HospedeService hospedeService;

    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @GetMapping
    public List<Hospede> listarHospedes() {
        return hospedeService.listar();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Hospede> consultar(@PathVariable String cpf) {
        Hospede hospede = hospedeService.consultarObjeto(cpf);

        return (hospede != null)
                ? ResponseEntity.ok(hospede)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> inserir(@Valid @RequestBody HospedeDTO dto) {
        String resultado = hospedeService.inserir(dto);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(resultado));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(resultado));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ApiResponse> atualizar(@PathVariable String cpf, @Valid @RequestBody HospedeDTO dto) {
        String resultado = hospedeService.atualizar(cpf, dto);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(resultado));
        }

        return ResponseEntity.ok(new ApiResponse(resultado));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<ApiResponse> excluir(@PathVariable String cpf) {
        String resultado = hospedeService.excluir(cpf);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(resultado));
        }

        return ResponseEntity.ok(new ApiResponse(resultado));
    }
}
