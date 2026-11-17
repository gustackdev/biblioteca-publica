public class FilaReservas {
    private static class No {
        String leitor;
        No proximo;

        No(String leitor) {
            this.leitor = leitor;
        }
    }

    private No primeiro;
    private No ultimo;

    public boolean inserir(String leitor) {
        if (buscar(leitor)) {
            return false;
        }

        No novo = new No(leitor);
        if (primeiro == null) {
            primeiro = novo;
        } else {
            ultimo.proximo = novo;
        }
        ultimo = novo;
        return true;
    }

    public boolean buscar(String leitor) {
        No atual = primeiro;
        while (atual != null) {
            if (atual.leitor.equalsIgnoreCase(leitor)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public String remover() {
        if (primeiro == null) {
            return null;
        }

        String leitor = primeiro.leitor;
        primeiro = primeiro.proximo;
        if (primeiro == null) {
            ultimo = null;
        }
        return leitor;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public void listar() {
        if (estaVazia()) {
            System.out.println("Nao ha reservas para este livro.");
            return;
        }

        No atual = primeiro;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + " - " + atual.leitor);
            atual = atual.proximo;
            posicao++;
        }
    }
}
