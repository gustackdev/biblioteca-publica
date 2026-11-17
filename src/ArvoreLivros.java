public class ArvoreLivros {
    private static class No {
        Livro livro;
        No esquerda;
        No direita;

        No(Livro livro) {
            this.livro = livro;
        }
    }

    private No raiz;

    public boolean inserir(Livro livro) {
        if (buscar(livro.getIsbn()) != null) {
            return false;
        }
        raiz = inserirNo(raiz, livro);
        return true;
    }

    private No inserirNo(No atual, Livro livro) {
        if (atual == null) {
            return new No(livro);
        }
        if (livro.getIsbn().compareTo(atual.livro.getIsbn()) < 0) {
            atual.esquerda = inserirNo(atual.esquerda, livro);
        } else {
            atual.direita = inserirNo(atual.direita, livro);
        }
        return atual;
    }

    public Livro buscar(String isbn) {
        No atual = raiz;
        while (atual != null) {
            int comparacao = isbn.compareTo(atual.livro.getIsbn());
            if (comparacao == 0) {
                return atual.livro;
            }
            if (comparacao < 0) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }
        return null;
    }

    public boolean remover(String isbn) {
        if (buscar(isbn) == null) {
            return false;
        }
        raiz = removerNo(raiz, isbn);
        return true;
    }

    private No removerNo(No atual, String isbn) {
        if (atual == null) {
            return null;
        }

        int comparacao = isbn.compareTo(atual.livro.getIsbn());
        if (comparacao < 0) {
            atual.esquerda = removerNo(atual.esquerda, isbn);
        } else if (comparacao > 0) {
            atual.direita = removerNo(atual.direita, isbn);
        } else {
            if (atual.esquerda == null) {
                return atual.direita;
            }
            if (atual.direita == null) {
                return atual.esquerda;
            }

            No sucessor = atual.direita;
            while (sucessor.esquerda != null) {
                sucessor = sucessor.esquerda;
            }
            atual.livro = sucessor.livro;
            atual.direita = removerNo(atual.direita, sucessor.livro.getIsbn());
        }
        return atual;
    }

    public void percursos() {
        if (raiz == null) {
            System.out.println("O acervo esta vazio.");
            return;
        }

        System.out.println("\nEM ORDEM");
        emOrdem(raiz);
        System.out.println("\nPRE-ORDEM");
        preOrdem(raiz);
        System.out.println("\nPOS-ORDEM");
        posOrdem(raiz);
    }

    private void emOrdem(No atual) {
        if (atual != null) {
            emOrdem(atual.esquerda);
            System.out.println(atual.livro);
            emOrdem(atual.direita);
        }
    }

    private void preOrdem(No atual) {
        if (atual != null) {
            System.out.println(atual.livro);
            preOrdem(atual.esquerda);
            preOrdem(atual.direita);
        }
    }

    private void posOrdem(No atual) {
        if (atual != null) {
            posOrdem(atual.esquerda);
            posOrdem(atual.direita);
            System.out.println(atual.livro);
        }
    }
}
