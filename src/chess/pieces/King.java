package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch; // ASSOCIAÇÃO COM A CLASSE CHESSMATCH E ADCIONANDO AO CONSTRUTOR

    public King(Board board, Color color, ChessMatch chessMatch) { // CONSTRUTOR
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }


    // METODO PARA SABER SE O REI PODE SE MOVER PARA UMA DETERMINADA POSIÇÃO
    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor(); // TARGET == NULL E COLOR DIFERENTE/PEÇA ADVERSARIA // PODERÁ SE MOVER PARA QUALQER LADO
    }

    private boolean testRookCastling(Position position) { // TESTAR SE A TORRE PODE FAZER O ROQUE
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0; // TESTAR SE A PEÇA É != NULL E SE É UMA INSTANCIA DE TORRE E SE A COR É IGUAL A COR DO REI E SE O MOVIMENTO DA TORRE É IGUAL A 0
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
        p.setValues(position.getRow(), position.getColumn() - 1);
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

        // # SPECIAL MOVE CASTLING
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // # SPECIALMOVE CASTLING KINGSIDE ROOK PEQUENO
            Position posT1 = new Position(position.getRow(), position.getColumn() + 3); // POSIÇÃO DA LINHA DO REI, POSIÇÃO DA COLUNA + 3 PEGANDO ONDE ESTÁ A TORRE DO REI
            if (testRookCastling(posT1)) {
                Position p1 = new Position(position.getRow(), position.getColumn() + 1); // TESTANDO SE AS POSIÇÕES ESTÃO VAZIAS
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) { // SE NO TABULEIRO NO P1 E P2 ESTIVEREM VAZIOS
                    mat[position.getRow()][position.getColumn() + 2] = true; // FAZ O ROQUE! PORQUE TODAS AS CONDIÇÕES FORAM ATENDIDAS
                }
            }
            // # SPECIALMOVE CASTLING QUEENSIDE ROOK GRANDE
            Position posT2 = new Position(position.getRow(), position.getColumn() - 4); // POSIÇÃO DA LINHA DO REI, POSIÇÃO DA COLUNA - 4 PEGANDO ONDE ESTÁ A TORRE DO REI
            if (testRookCastling(posT2)) {
                Position p1 = new Position(position.getRow(), position.getColumn() - 1); // TESTANDO SE AS POSIÇÕES ESTÃO VAZIAS
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) { // SE NO TABULEIRO NO P1 E P2 ESTIVEREM VAZIOS
                    mat[position.getRow()][position.getColumn() - 2] = true; // FAZ O ROQUE
                }
            }
        }

        return mat;
    }
}
