package br.edu.fatecfranca;

import java.util.Scanner;

public class AppBiblioteca {
    public static void main(String[] args) {
        LivrosDAO dao = new LivrosDAO();
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU LIVROS ---");
            System.out.println("1. Novo Livro | 2. Mudar Status | 3. Remover | 4. Listar | 0. Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String tit = sc.nextLine();
                    System.out.print("Autor: ");
                    String aut = sc.nextLine();
                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Editora: ");
                    String ed = sc.nextLine();
                    dao.inserir(tit, aut, ano, ed);
                    break;
                case 2:
                    System.out.print("ID do livro: ");
                    int idS = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo Status (disponivel/emprestado): ");
                    String st = sc.nextLine();
                    dao.atualizarStatus(idS, st);
                    break;
                case 3:
                    System.out.print("ID para remover: ");
                    dao.remover(sc.nextInt());
                    break;
                case 4:
                    for(String l : dao.listar()) System.out.println(l);
                    break;
            }
        } while (opcao != 0);
    }
}