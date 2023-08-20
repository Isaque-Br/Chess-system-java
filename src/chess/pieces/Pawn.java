package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; // DECLARANDO UMA MATRIZ COM O MESMO NUMERO DE LINHAS E COLUNAS DO TABULEIRO    }
        Position p = new Position(0, 0); // DECLARANDO UMA POSIÇÃO

        if (getColor() == Color.YELLOW) { // TESTANDO SE A PEÇA É yellow
            p.setValues(position.getRow() - 1, position.getColumn()); // REGRA DE ANDAR PARA CIMA NA MATRIZ = -1
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // TESTANDO SE A POSIÇÃO EXISTE E SE NÃO TEM PEÇA
                mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
            }
                p.setValues(position.getRow() - 2, position.getColumn()); // REGRA DE ANDAR DUAS PARA CIMA NA MATRIZ = -2
                Position p2 = new Position(position.getRow() - 1, position.getColumn()); // POSIÇÃO DOIS TSTANDO SE A POSIÇÃO EXISTE E SE NÃO TEM PEÇA
                if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) { // TESTANDO SE A POSIÇÃO EXISTE E SE NÃO TEM PEÇA E SE NUNCA FOI MOVIDA
                    mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
                }
                p.setValues(position.getRow() - 1, position.getColumn() - 1); // REGRA PARA ANDAR NA DIAGONAL ESQUERDA
                if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // TESTANDO SE A POSIÇÃO EXISTE E SE TEM PEÇA OPONENTE
                    mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
                }
                p.setValues(position.getRow() - 1, position.getColumn() + 1); // REGRA PARA ANDAR NA DIAGONAL DIREITA
                if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // TESTANDO SE A POSIÇÃO EXISTE E SE TEM PEÇA OPONENTE
                    mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
                }
            } else {
                p.setValues(position.getRow() + 1, position.getColumn()); // REGRA DE ANDAR PARA BAIXO NA MATRIZ = +1
                if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // TESTANDO SE A POSIÇÃO EXISTE E SE NÃO TEM PEÇA
                    mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
                }
                p.setValues(position.getRow() + 2, position.getColumn()); // REGRA DE ANDAR DUAS PARA BAIXO NA MATRIZ = +2
                Position p2 = new Position(position.getRow() + 1, position.getColumn()); // POSIÇÃO DOIS TSTANDO SE A POSIÇÃO EXISTE E SE NÃO TEM PEÇA
                if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) { // TESTANDO SE A POSIÇÃO EXISTE E SE NÃO TEM PEÇA E SE NUNCA FOI MOVIDA
                    mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
                }
                p.setValues(position.getRow() + 1, position.getColumn() - 1); // REGRA PARA ANDAR NA DIAGONAL ESQUERDA
                if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // TESTANDO SE A POSIÇÃO EXISTE E SE TEM PEÇA OPONENTE
                    mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
                }
                p.setValues(position.getRow() + 1, position.getColumn() + 1); // REGRA PARA ANDAR NA DIAGONAL DIREITA
                if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // TESTANDO SE A POSIÇÃO EXISTE E SE TEM PEÇA OPONENTE
                    mat[p.getRow()][p.getColumn()] = true; // INDICA QUE A PEÇA PODE SER MOVIDA
                }
            }
            return mat;
        }

        @Override
        public String toString () {
            return "P";
        }
    }
