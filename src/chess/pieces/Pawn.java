package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch; // DECLARANDO DEPENDENCIA PARA A PARTIDA

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch; // FEITA A ASSOCIAÇÃO ENTRE OS OBJETOS
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

                // #Special move en passant yellow
                if (position.getRow() == 3) {
                    Position left = new Position(position.getRow(), position.getColumn() - 1);
                    if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                        mat[left.getRow() - 1][left.getColumn()] = true;
                    }
                    Position right = new Position(position.getRow(), position.getColumn() + 1);
                    if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                        mat[right.getRow() - 1][right.getColumn()] = true;
                    }
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

            // #Special move en passant blue
            if (position.getRow() == 4) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    mat[right.getRow() + 1][right.getColumn()] = true;
                }
            }
            }
            return mat;
        }

        @Override
        public String toString () {
            return "P";
        }
    }
