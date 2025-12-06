package br.edu.fatecfranca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    public void inserir(String nome, String email, String telefone) {
        String sql = "INSERT INTO usuarios (nome, email, telefone) VALUES (?, ?, ?)";
        
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            
            stmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<String> listar() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY id";
        
        try (Connection conexao = ConnectionFactory.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add("ID: " + rs.getInt("id") + " - Nome: " + rs.getString("nome") 
                        + " - Email: " + rs.getString("email") + " - Tel: " + rs.getString("telefone"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(int id, String nome, String email, String telefone) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, telefone = ? WHERE id = ?";
        
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setInt(4, id);
            
            if (stmt.executeUpdate() > 0) System.out.println("Usuário atualizado!");
            else System.out.println("Usuário não encontrado.");
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0) System.out.println("Usuário removido!");
            else System.out.println("ID não encontrado.");
            
        } catch (SQLException e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }
}