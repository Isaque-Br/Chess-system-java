package chess.pieces; // COLOCANDO AS PEÇAS DO XADREZ NO SUB-PACOTE PRA FICAR ORGANIADO

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";  // CONVERTENDO UMA ROOK/TORRE PARA STRING
    }
}
