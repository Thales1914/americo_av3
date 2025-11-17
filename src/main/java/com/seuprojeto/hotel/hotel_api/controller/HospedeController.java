package com.seuprojeto.hotel.hotel_api.controller;

import com.seuprojeto.hotel.hotel_api.model.Hospede;
import com.seuprojeto.hotel.hotel_api.model.ApiResponse;
import com.seuprojeto.hotel.hotel_api.service.HospedeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Hospede> consultarHospede(@PathVariable String cpf) {
        Hospede hospede = hospedeService.consultarObjeto(cpf);

        if (hospede != null) {
            return ResponseEntity.ok(hospede);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> inserirHospede(@RequestBody Hospede novoHospede) {
        String resultado = hospedeService.inserir(novoHospede);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(resultado)); // Retorna JSON de erro
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(resultado)); // Retorna JSON de sucesso
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ApiResponse> atualizarHospede(@PathVariable String cpf, @RequestBody Hospede dadosAtualizados) {
        String resultado = hospedeService.atualizar(cpf, dadosAtualizados);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(resultado));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(resultado));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<ApiResponse> excluirHospede(@PathVariable String cpf) {
        String resultado = hospedeService.excluir(cpf);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(resultado));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(resultado));
    }
}