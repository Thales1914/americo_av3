package com.seuprojeto.hotel.hotel_api.repository;

import com.seuprojeto.hotel.hotel_api.model.Hospede;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HospedeRepository {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/hotel_db", "postgres", "300903tmb");
    }

    public void inserir(Hospede h) throws SQLException {
        String sql = "INSERT INTO hospede (cpf, nome, idade, rg, fidelidade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, h.getCpf());
            pstmt.setString(2, h.getNome());
            pstmt.setInt(3, h.getIdade());
            pstmt.setString(4, h.getRg());
            pstmt.setBoolean(5, h.isFidelidade());
            pstmt.executeUpdate();
        }
    }

    public List<Hospede> findAll() throws SQLException {
        List<Hospede> hospedes = new ArrayList<>();
        String sql = "SELECT * FROM hospede";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Hospede h = new Hospede(
                        rs.getString("cpf"), rs.getString("nome"), rs.getInt("idade"),
                        rs.getString("rg"), rs.getBoolean("fidelidade")
                );
                hospedes.add(h);
            }
        }
        return hospedes;
    }

    public Hospede findByCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM hospede WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Hospede(
                            rs.getString("cpf"), rs.getString("nome"), rs.getInt("idade"),
                            rs.getString("rg"), rs.getBoolean("fidelidade")
                    );
                }
            }
        }
        return null;
    }

    public boolean atualizar(Hospede h) throws SQLException {
        String sql = "UPDATE hospede SET nome = ?, idade = ?, rg = ?, fidelidade = ? WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, h.getNome());
            pstmt.setInt(2, h.getIdade());
            pstmt.setString(3, h.getRg());
            pstmt.setBoolean(4, h.isFidelidade());
            pstmt.setString(5, h.getCpf());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean excluir(String cpf) throws SQLException {
        String sql = "DELETE FROM hospede WHERE cpf = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}