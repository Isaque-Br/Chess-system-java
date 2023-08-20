package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

// CLASS ABSTRATA DO PACOTE CHESS QUE HERDA DE PEÇA - SUBCLASS DA PIECE
public abstract class ChessPiece extends Piece {
    private Color color;
    private int moveCount; // CONTADOR DE MOVIMENTOS / POR PADRAO O JAVA INICIA COM 0

    // POR SER SUBCLASS DE PEÇA, É NECESSARIO IMPLEMENTAR O CONSTRUTOR DA CLASS PIECE
    public ChessPiece(Board board, Color color) {
        super(board); // SUPER CONSTRUTOR QUE RECEBE O BOARD
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount() { // METODO PARA INCREMENTAR O MOVIMENTO
        moveCount++;
    }

    public void decreaseMoveCount() { // METODO PARA DECREMENTAR O MOVIMENTO
        moveCount--;
    }

    public ChessPosition getChessPosition() { // METODO PARA RETORNAR UMA POSIÇÃO DO XADREZ
        return ChessPosition.fromPosition(position);
    }

    // PROTECTED PARA SER ACESSIVEL SOMENTE PARA O PACOTE E SUBCLASS(PIECES)KING/ROOK
    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color; //TESTA SE A COR DA POSITION DO METODO É DIFERENTE DA PEÇA ADVRSARIA
    }
}
