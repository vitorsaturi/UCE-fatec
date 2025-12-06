package br.edu.fatecfranca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivrosDAO {

    public void inserir(String titulo, String autor, int ano, String editora) {
        String sql = "INSERT INTO livros (titulo, autor, ano_publicacao, editora, status) VALUES (?, ?, ?, ?, 'disponivel')";
        
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setInt(3, ano);
            stmt.setString(4, editora);
            
            stmt.executeUpdate();
            System.out.println("Livro inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<String> listar() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT * FROM livros ORDER BY id";
        
        try (Connection conexao = ConnectionFactory.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(rs.getInt("id") + " - " + rs.getString("titulo") 
                        + " (" + rs.getString("status") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarStatus(int id, String novoStatus) {
        String sql = "UPDATE livros SET status = ? WHERE id = ?";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, novoStatus);
            stmt.setInt(2, id);
            
            if (stmt.executeUpdate() > 0) System.out.println("Status atualizado!");
            else System.out.println("Livro não encontrado.");
            
        } catch (SQLException e) {
            System.out.println("Erro status: " + e.getMessage());
        }
    }
    
    public void remover(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0) System.out.println("Livro removido.");
            else System.out.println("Não encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }
}