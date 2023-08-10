package chess;

import boardgame.Board;
import boardgame.Piece;

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

}
