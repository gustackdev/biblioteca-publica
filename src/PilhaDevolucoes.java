public class PilhaDevolucoes {
    private static class No {
        Livro livro;
        No proximo;

        No(Livro livro) {
            this.livro = livro;
        }
    }

    private No topo;

    public void inserir(Livro livro) {
        No novo = new No(livro);
        novo.proximo = topo;
        topo = novo;
    }

    public Livro buscar(String isbn) {
        No atual = topo;
        while (atual != null) {
            if (atual.livro.getIsbn().equals(isbn)) {
                return atual.livro;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public Livro remover() {
        if (topo == null) {
            return null;
        }

        Livro livro = topo.livro;
        topo = topo.proximo;
        return livro;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void listar() {
        if (estaVazia()) {
            System.out.println("Nao ha devolucoes aguardando reposicao.");
            return;
        }

        No atual = topo;
        while (atual != null) {
            System.out.println(atual.livro.getIsbn() + " - " + atual.livro.getTitulo());
            atual = atual.proximo;
        }
    }
}
