package com.seuprojeto.hotel.hotel_api.controller;

import com.seuprojeto.hotel.hotel_api.model.Funcionario;
import com.seuprojeto.hotel.hotel_api.model.ApiResponse;
import com.seuprojeto.hotel.hotel_api.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<ApiResponse> inserirFuncionario(@RequestBody Funcionario novoFuncionario) {
        String resultado = funcionarioService.inserir(novoFuncionario);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(resultado));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(resultado));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Funcionario> consultarFuncionario(@PathVariable String cpf) {
        Funcionario funcionario = funcionarioService.consultarObjeto(cpf);

        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ApiResponse> atualizarFuncionario(@PathVariable String cpf, @RequestBody Funcionario dadosAtualizados) {
        String resultado = funcionarioService.atualizar(cpf, dadosAtualizados);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(resultado));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(resultado));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<ApiResponse> excluirFuncionario(@PathVariable String cpf) {
        String resultado = funcionarioService.excluir(cpf);

        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(resultado));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(resultado));
    }
}