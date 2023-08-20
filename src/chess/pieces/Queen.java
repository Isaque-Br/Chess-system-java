package chess.pieces; // COLOCANDO AS PEÇAS DO XADREZ NO SUB-PACOTE PRA FICAR ORGANIADO

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q";  // REPRESENTAÇÃO DA PEÇA NO TABULEIRO
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

        //NW - NOROESTE / POSIÇÃO DA PEÇA - NA POSIÇÃO DIAGONAL DELA / ANALISANDO ACIMA DA PEÇA
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // ENQUANTO A POSITION EXISITR E TIVER VAGA, SERÁ TRUE
            mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
            p.setValues(p.getRow() - 1, p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // NE - NORDESTE / POSIÇÃO DA PEÇA - NA POSIÇÃO DIAGONAL DELA / ANALISANDO ACIMA DA PEÇA
        p.setValues(position.getRow() - 1, position.getColumn() + 1); // - 1 NA COLUNA
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
            p.setValues(p.getRow() - 1, p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // SUDEST - SULDESTE
        p.setValues(position.getRow() + 1, position.getColumn() + 1); // - 1 NA COLUNA
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
            p.setValues(p.getRow() + 1, p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // SW - SUDOESTE
        p.setValues(position.getRow() + 1, position.getColumn() - 1); // - 1
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // ENQUANTO A POSITION EXISITR E TIVER VAGA, SERÁ TRUE
            mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
            p.setValues(p.getRow() + 1, p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
