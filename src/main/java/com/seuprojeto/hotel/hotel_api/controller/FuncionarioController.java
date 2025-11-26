package com.seuprojeto.hotel.hotel_api.controller;

import com.seuprojeto.hotel.hotel_api.model.Funcionario;
import com.seuprojeto.hotel.hotel_api.model.ApiResponse;
import com.seuprojeto.hotel.hotel_api.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Funcionario> listar() {
        return service.listar();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Funcionario> consultar(@PathVariable String cpf) {
        Funcionario funcionario = service.consultarObjeto(cpf);
        return funcionario != null ? ResponseEntity.ok(funcionario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> inserir(@RequestBody Funcionario funcionario) {
        String resultado = service.inserir(funcionario);
        return ResponseEntity.ok(new ApiResponse(resultado));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ApiResponse> atualizar(@PathVariable String cpf, @RequestBody Funcionario funcionario) {
        String resultado = service.atualizar(cpf, funcionario);
        return ResponseEntity.ok(new ApiResponse(resultado));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<ApiResponse> excluir(@PathVariable String cpf) {
        String resultado = service.excluir(cpf);
        return ResponseEntity.ok(new ApiResponse(resultado));
    }
}
