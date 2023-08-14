package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
    private Color color;

    // POR SER SUBCLASS DE PEÇA, É NEESSARIO IMPLEMENTAR O CONSTRUTOR DA CLASS PIECE
    public ChessPiece(Board board, Color color) {
        super(board); // SUPER CONSTRUTOR QUE RECEBE O BOARD
        this.color = color;
    }

    public Color getColor() {
        return color;
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
