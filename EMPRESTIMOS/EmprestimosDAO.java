package br.edu.fatecfranca;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimosDAO {

    public void inserir(int livro_id, int usuario_id, LocalDate data_emprestimo, LocalDate data_devolucao) {
        String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao, status) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, livro_id);
            stmt.setInt(2, usuario_id);
            stmt.setDate(3, Date.valueOf(data_emprestimo));
            stmt.setDate(4, Date.valueOf(data_devolucao));
            stmt.setString(5, "ativo");
            
            stmt.executeUpdate();
            System.out.println("Empréstimo registrado!");
        } catch (SQLException e) {
            System.out.println("Erro ao emprestar: " + e.getMessage());
        }
    }

    public List<String> listar() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos ORDER BY id";
        
        try (Connection conexao = ConnectionFactory.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add("ID Emp: " + rs.getInt("id") + " | Livro ID: " + rs.getInt("livro_id") 
                        + " | User ID: " + rs.getInt("usuario_id") 
                        + " | Devolução: " + rs.getDate("data_devolucao") 
                        + " | Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    public void finalizarEmprestimo(int id) {
        String sql = "UPDATE emprestimos SET status = 'concluido' WHERE id = ?";
        
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0) System.out.println("Empréstimo concluído!");
            else System.out.println("Empréstimo não encontrado.");
            
        } catch (SQLException e) {
            System.out.println("Erro ao finalizar: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM emprestimos WHERE id = ?";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0) System.out.println("Registro removido.");
            else System.out.println("Não encontrado.");
            
        } catch (SQLException e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }
}