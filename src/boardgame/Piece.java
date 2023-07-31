package boardgame;

public class Piece { // CLASSE PEÇA

    // PRA NÃO SER VISIVEL NA CAMADA DE XADREZ
    protected Position position;

    private Board board;

    public Piece(Board board) { // COSTRUTOR APENAS COM TABULEIRO NA HORA DE CRIAR A PEÇA
        this.board = board;
        position = null; // POSIÇÃO DE UMA PEÇA RECEM CRIADA IGUAL NULO
    }

    // SOMENTE CLASSES E SUBCLSSES DENTRO DO MESMO PACOTE PODERÃO TER ACESSO AO TABULEIRO DE UMA PEÇA
    protected Board getBoard() {
        return board;
    }

}
