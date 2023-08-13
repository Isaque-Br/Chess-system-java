package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }


    // METODO PARA SABER SE O REI PODE SE MOVER PARA UMA DETERMINADA POSIÇÃO
    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor(); // TARGET == NULL E COLOR DIFERENTE/PEÇA ADVERSARIA // PODERÁ SE MOVER PARA QUALQER LADO
    }
    @Override
    public boolean[][] possibleMoves() { // MATRIX COM O MESMO NUMERO  DE LINHAS E COLUNA DO TABU
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // TESTAR AS 8 DIREÇOES POSSIVEIS QUE O REI POSSA SE MOVER

        // ABOVE
          // PEGA A POSITION P E DEFINIR PRA ESSA POSITION, OS VALORES DA POSITION ACIMA DO REI
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // BELOW
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // LEFT
        p.setValues(position.getRow(), position.getColumn() -1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // RIGHT
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // NW - NOROESTE      // LINHA DO REI - 1...
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // NE - NORDESTE      // LINHA DO REI - 1...
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // SW - SUDOESTE      // LINHA DO REI + 1... E COLUNA - 1
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // SE - SUDESTE
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
