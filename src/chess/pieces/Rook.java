package chess.pieces; // COLOCANDO AS PEÇAS DO XADREZ NO SUB-PACOTE PRA FICAR ORGANIADO

import boardgame.Board;
import boardgame.Position;
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

        Position p = new Position(0, 0);

        //ABOVE/ POSIÇÃO DA PEÇA - 1 DA LINHA E COLUNA DELA / ANALISANDO ACIMA DA PEÇA
        p.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // ENQUANTO A POSITION EXISITR E TIVER VAGA, SERÁ TRUE
            mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // LEFT
        p.setValues(position.getRow(), position.getColumn() - 1); // - 1 NA COLUNA
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // RIGHT
        p.setValues(position.getRow(), position.getColumn() + 1); // - 1 NA COLUNA
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // BELOW
        p.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // ENQUANTO A POSITION EXISITR E TIVER VAGA, SERÁ TRUE
            mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
