public class Biblioteca {
    private final ArvoreLivros acervo = new ArvoreLivros();
    private final PilhaDevolucoes devolucoes = new PilhaDevolucoes();

    public boolean cadastrarLivro(String isbn, String titulo, String autor) {
        return acervo.inserir(new Livro(isbn, titulo, autor));
    }

    public Livro buscarLivro(String isbn) {
        return acervo.buscar(isbn);
    }

    public boolean excluirLivro(String isbn) {
        Livro livro = buscarLivro(isbn);
        if (livro == null || !livro.estaDisponivel()
                || !livro.getReservas().estaVazia()) {
            return false;
        }
        return acervo.remover(isbn);
    }

    public boolean emprestarLivro(String isbn, String leitor) {
        Livro livro = buscarLivro(isbn);
        if (livro == null || !livro.estaDisponivel()) {
            return false;
        }
        livro.emprestar(leitor);
        return true;
    }

    public boolean reservarLivro(String isbn, String leitor) {
        Livro livro = buscarLivro(isbn);
        if (livro == null || !livro.estaEmprestado()) {
            return false;
        }
        if (livro.getLeitorAtual().equalsIgnoreCase(leitor)) {
            return false;
        }
        return livro.getReservas().inserir(leitor);
    }

    public boolean devolverLivro(String isbn) {
        Livro livro = buscarLivro(isbn);
        if (livro == null || !livro.estaEmprestado() || devolucoes.buscar(isbn) != null) {
            return false;
        }
        livro.devolver();
        devolucoes.inserir(livro);
        return true;
    }

    public Livro reporProximoLivro() {
        Livro livro = devolucoes.remover();
        if (livro == null) {
            return null;
        }

        String proximoLeitor = livro.getReservas().remover();
        if (proximoLeitor == null) {
            livro.repor();
        } else {
            livro.emprestar(proximoLeitor);
        }
        return livro;
    }

    public void listarAcervo() {
        acervo.percursos();
    }

    public void listarDevolucoes() {
        devolucoes.listar();
    }
}
