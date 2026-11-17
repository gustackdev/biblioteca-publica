import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        int opcao;

        do {
            System.out.println("\n===== BIBLIOTECA PUBLICA =====");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Buscar livro pelo ISBN");
            System.out.println("3 - Excluir livro");
            System.out.println("4 - Listar acervo (3 percursos)");
            System.out.println("5 - Emprestar livro");
            System.out.println("6 - Reservar livro");
            System.out.println("7 - Consultar fila de reservas");
            System.out.println("8 - Registrar devolucao");
            System.out.println("9 - Repor proximo livro");
            System.out.println("10 - Listar devolucoes do dia");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            String isbn;
            String leitor;
            Livro livro;

            switch (opcao) {
                case 1:
                    isbn = lerTexto(scanner, "ISBN: ");
                    String titulo = lerTexto(scanner, "Titulo: ");
                    String autor = lerTexto(scanner, "Autor: ");
                    if (biblioteca.cadastrarLivro(isbn, titulo, autor)) {
                        System.out.println("Livro cadastrado.");
                    } else {
                        System.out.println("Ja existe um livro com esse ISBN.");
                    }
                    break;
                case 2:
                    isbn = lerTexto(scanner, "ISBN: ");
                    livro = biblioteca.buscarLivro(isbn);
                    System.out.println(livro == null ? "Livro nao encontrado." : livro);
                    break;
                case 3:
                    isbn = lerTexto(scanner, "ISBN: ");
                    livro = biblioteca.buscarLivro(isbn);
                    if (livro == null) {
                        System.out.println("Livro nao encontrado.");
                    } else if (biblioteca.excluirLivro(isbn)) {
                        System.out.println("Livro excluido.");
                    } else {
                        System.out.println("Nao e possivel excluir um livro emprestado, "
                                + "reservado ou em reposicao.");
                    }
                    break;
                case 4:
                    biblioteca.listarAcervo();
                    break;
                case 5:
                    isbn = lerTexto(scanner, "ISBN: ");
                    leitor = lerTexto(scanner, "Nome do leitor: ");
                    if (biblioteca.emprestarLivro(isbn, leitor)) {
                        System.out.println("Emprestimo registrado.");
                    } else {
                        System.out.println("Livro nao encontrado ou indisponivel.");
                    }
                    break;
                case 6:
                    isbn = lerTexto(scanner, "ISBN: ");
                    leitor = lerTexto(scanner, "Nome do leitor: ");
                    if (biblioteca.reservarLivro(isbn, leitor)) {
                        System.out.println("Reserva registrada na fila.");
                    } else {
                        System.out.println("Reserva nao realizada. Verifique o emprestimo "
                                + "ou o nome informado.");
                    }
                    break;
                case 7:
                    isbn = lerTexto(scanner, "ISBN: ");
                    livro = biblioteca.buscarLivro(isbn);
                    if (livro == null) {
                        System.out.println("Livro nao encontrado.");
                    } else {
                        livro.getReservas().listar();
                    }
                    break;
                case 8:
                    isbn = lerTexto(scanner, "ISBN: ");
                    if (biblioteca.devolverLivro(isbn)) {
                        System.out.println("Devolucao registrada na pilha para reposicao.");
                    } else {
                        System.out.println("Livro nao encontrado ou nao esta emprestado.");
                    }
                    break;
                case 9:
                    livro = biblioteca.reporProximoLivro();
                    if (livro == null) {
                        System.out.println("Nao ha livros para repor.");
                    } else if (livro.estaEmprestado()) {
                        System.out.println("Livro " + livro.getTitulo()
                                + " destinado a reserva de " + livro.getLeitorAtual() + ".");
                    } else {
                        System.out.println("Livro " + livro.getTitulo()
                                + " devolvido a prateleira.");
                    }
                    break;
                case 10:
                    biblioteca.listarDevolucoes();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static String lerTexto(Scanner scanner, String mensagem) {
        String texto;
        do {
            System.out.print(mensagem);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Este campo nao pode ficar vazio.");
            }
        } while (texto.isEmpty());
        return texto;
    }
}
