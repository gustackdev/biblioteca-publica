public class Livro {
    private final String isbn;
    private final String titulo;
    private final String autor;
    private String leitorAtual;
    private boolean aguardandoReposicao;
    private final FilaReservas reservas;

    public Livro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.reservas = new FilaReservas();
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public FilaReservas getReservas() {
        return reservas;
    }

    public String getLeitorAtual() {
        return leitorAtual;
    }

    public boolean estaEmprestado() {
        return leitorAtual != null;
    }

    public boolean estaEmReposicao() {
        return aguardandoReposicao;
    }

    public boolean estaDisponivel() {
        return !estaEmprestado() && !aguardandoReposicao;
    }

    public void emprestar(String leitor) {
        leitorAtual = leitor;
        aguardandoReposicao = false;
    }

    public void devolver() {
        leitorAtual = null;
        aguardandoReposicao = true;
    }

    public void repor() {
        aguardandoReposicao = false;
    }

    public String getSituacao() {
        if (estaEmprestado()) {
            return "Emprestado para " + leitorAtual;
        }
        if (aguardandoReposicao) {
            return "Aguardando reposicao";
        }
        return "Disponivel";
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + " | Titulo: " + titulo + " | Autor: " + autor
                + " | Situacao: " + getSituacao();
    }
}
