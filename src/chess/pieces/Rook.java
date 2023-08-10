package chess.pieces; // COLOCANDO AS PEÇAS DO XADREZ NO SUB-PACOTE PRA FICAR ORGANIADO

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) { // CONSTUTOR QUE REPASSA A CHAMADA PRA SUPER CLASS
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";  // CONVERTENDO UMA ROOK/TORRE PARA STRING
    }

    @Override
    public boolean[][] possibleMoves() { // MATRIX COM O MESMO NUMERO  DE LINHAS E COLUNA DO TABU
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
}
