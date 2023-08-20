package boardgame;

public abstract class Piece { // CLASSE PEÇA

    // POSIÇÃO SIMPLES DE MATRIX PRA NÃO SER VISIVEL NA CAMADA DE XADREZ
    protected Position position;

    private Board board; // PEÇA TEM UMA REFERENCIA PARA O TABULEIRO / ASSOCIAÇÃO

    public Piece(Board board) { // COSTRUTOR PRA CRIAR PIECE TEM QUE CRIAR O BOARD
        this.board = board;
        position = null; // POSIÇÃO DE UMA PEÇA RECEM CRIADA IGUAL NULO / NÃO FOI COLOCADO NA TABULEIRO AINDA
    }

    // SOMENTE CLASSES DENTRO DO MESMO PACOTE E SUBCLSSES(ChessPiece) E PEÇAS ESPECIFICAS COMO, KING, BISHOP..  PODERÃO TER ACESSO AO TABULEIRO DE UMA PEÇA
    protected Board getBoard() {
        return board;
    } // DE USO INTERNO DA CAMADA BOARDGAME

    public abstract boolean[][] possibleMoves(); // MOVIMENTOS POSSIVEIS DE UMA PEÇA GENERICA POR ISSO ABSTRATA

    // METODO CONCRETO QUE UTILIZA METODO ABSTRATO
    public boolean possibleMove(Position position) { //METODO QUE VERIFICA SE É POSSIVEL MOVER A PEÇA PARA A DADA POSIÇÃO
        return possibleMoves()[position.getRow()][position.getColumn()];
    } //METODO QUE FAZ GANCHO COM A SUBCLASS
     // PADRÃO DE PROJETO TEMPLATE METHODS,CONSEGUIR FORNECER UMA IMPLEMENTAÇÃO PADRÃO DE UM ETODO QUE DEPENDE DE UM METODO ABSTRATO

    public boolean isThereAnyPossibleMove() { // OPERAÇÃO QUE VERIFICA SE PELO MENOS EXISTE UM MOVIMENTO Para essa PEÇA
        boolean[][] mat = possibleMoves(); // PERCORRE A MATRIX PRA VER SE A POSIÇÃO É VERDADEIRA
        for (int i = 0; i<mat.length; i++) {
            for (int j = 0; j< mat.length; j++) { // PERCORRENDO MATRIX QUANDO FOR QUADRADA
                if (mat[i][j]) { // SE A ATRIX NA LINHA i E COLUNA j FOR VERDADEIRA, É POSSIVEL
                    return true;
                }
            }
        }
        return false; // SE PERCORRER TODA A KATRIX E NAO VOLTAR TRUE, É FALSE
    }
}
