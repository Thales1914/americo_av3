package com.seuprojeto.hotel.hotel_api.controller;

import com.seuprojeto.hotel.hotel_api.model.Hospede;
import com.seuprojeto.hotel.hotel_api.model.ApiResponse;
import com.seuprojeto.hotel.hotel_api.service.HospedeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeController {

    private final HospedeService service;

    public HospedeController(HospedeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Hospede> listar() {
        return service.listar();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Hospede> consultar(@PathVariable String cpf) {
        Hospede hospede = service.consultarObjeto(cpf);
        return hospede != null ? ResponseEntity.ok(hospede) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> inserir(@RequestBody Hospede hospede) {
        String resultado = service.inserir(hospede);
        return ResponseEntity.ok(new ApiResponse(resultado));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ApiResponse> atualizar(@PathVariable String cpf, @RequestBody Hospede hospede) {
        String resultado = service.atualizar(cpf, hospede);
        return ResponseEntity.ok(new ApiResponse(resultado));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<ApiResponse> excluir(@PathVariable String cpf) {
        String resultado = service.excluir(cpf);
        return ResponseEntity.ok(new ApiResponse(resultado));
    }
}
