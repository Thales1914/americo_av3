package com.seuprojeto.hotel.hotel_api.repository;

import com.seuprojeto.hotel.hotel_api.model.Funcionario;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FuncionarioRepository {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/hotel_db", "postgres", "300903tmb");
    }

    public void inserir(Funcionario f) throws SQLException {
        String sql = "INSERT INTO funcionario (cpf, nome, idade, funcao) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, f.getCpf());
            pstmt.setString(2, f.getNome());
            pstmt.setInt(3, f.getIdade());
            pstmt.setString(4, f.getFuncao());

            pstmt.executeUpdate();
        }
    }

    public List<Funcionario> findAll() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario f = new Funcionario(
                        rs.getString("cpf"), rs.getString("nome"), rs.getInt("idade"),
                        rs.getString("funcao")
                );
                funcionarios.add(f);
            }
        }
        return funcionarios;
    }

    public Funcionario findByCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Funcionario(
                            rs.getString("cpf"), rs.getString("nome"), rs.getInt("idade"),
                            rs.getString("funcao")
                    );
                }
            }
        }
        return null;
    }

    public boolean atualizar(Funcionario f) throws SQLException {
        String sql = "UPDATE funcionario SET nome = ?, idade = ?, funcao = ? WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, f.getNome());
            pstmt.setInt(2, f.getIdade());
            pstmt.setString(3, f.getFuncao());
            pstmt.setString(4, f.getCpf());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean excluir(String cpf) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}