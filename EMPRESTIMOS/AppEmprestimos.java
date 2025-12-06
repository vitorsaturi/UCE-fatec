package br.edu.fatecfranca;

import java.time.LocalDate;
import java.util.Scanner;

public class AppEmprestimos {
    public static void main(String[] args) {
        EmprestimosDAO dao = new EmprestimosDAO();
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU EMPRÉSTIMOS ---");
            System.out.println("1. Novo Empréstimo | 2. Finalizar (Devolver) | 3. Listar | 4. Remover | 0. Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    try {
                        System.out.print("ID do Livro: ");
                        int idLivro = sc.nextInt();
                        System.out.print("ID do Usuário: ");
                        int idUser = sc.nextInt();
                        sc.nextLine();
                        
                        System.out.print("Data Empréstimo (AAAA-MM-DD): ");
                        LocalDate dtEmp = LocalDate.parse(sc.nextLine());
                        
                        System.out.print("Data Devolução (AAAA-MM-DD): ");
                        LocalDate dtDev = LocalDate.parse(sc.nextLine());
                        
                        dao.inserir(idLivro, idUser, dtEmp, dtDev);
                    } catch (Exception e) {
                        System.out.println("Erro nos dados (Verifique o formato da data AAAA-MM-DD)");
                    }
                    break;
                case 2:
                    System.out.print("ID do Empréstimo para finalizar: ");
                    dao.finalizarEmprestimo(sc.nextInt());
                    break;
                case 3:
                    for(String e : dao.listar()) System.out.println(e);
                    break;
                case 4:
                    System.out.print("ID para remover do histórico: ");
                    dao.remover(sc.nextInt());
                    break;
            }
        } while (opcao != 0);
    }
}