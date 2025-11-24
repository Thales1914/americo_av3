package com.seuprojeto.hotel.hotel_api.controller;

import com.seuprojeto.hotel.hotel_api.dto.FuncionarioDTO;
import com.seuprojeto.hotel.hotel_api.model.Funcionario;
import com.seuprojeto.hotel.hotel_api.model.ApiResponse;
import com.seuprojeto.hotel.hotel_api.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listar();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> inserir(@Valid @RequestBody FuncionarioDTO dto) {
        String resultado = funcionarioService.inserir(dto);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(resultado));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(resultado));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Funcionario> consultar(@PathVariable String cpf) {
        Funcionario funcionario = funcionarioService.consultarObjeto(cpf);

        return (funcionario != null)
                ? ResponseEntity.ok(funcionario)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ApiResponse> atualizar(@PathVariable String cpf, @Valid @RequestBody FuncionarioDTO dto) {
        String resultado = funcionarioService.atualizar(cpf, dto);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(resultado));
        }
        return ResponseEntity.ok(new ApiResponse(resultado));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<ApiResponse> excluir(@PathVariable String cpf) {
        String resultado = funcionarioService.excluir(cpf);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(resultado));
        }

        return ResponseEntity.ok(new ApiResponse(resultado));
    }
}
