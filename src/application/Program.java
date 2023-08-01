package application;

import boardgame.Board;
import chess.ChessMatch;

public class Program {

    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces()); // CLASSE USER INTERFACE RECEBENDO O METODO DA MATRIX DO BOARD DA PARTIDA
    }
}
