package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>(); // ESSA LISTA DE PEÇAS CAPTURED VAI PASSAR TB COMO ARGUMENTO PARA IMPRIMIR PARTIDA

        while (!chessMatch.getCheckMate()) {  // ENQUANTO A PARTIDA NAO ESTIVER EM CHECKMATE, VAI RODAR O PROGRAMA
            try {
                UI.printMatch(chessMatch, captured); // CLASSE USER INTERFACE RECEBENDO O METODO DA MATRIX DO BOARD DA PARTIDA
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc); // USUARIO DIGITANDO POSIÇÃO DE ORIGEM

                boolean[][] possibleMoves = chessMatch.possibleMoves(source); // POSSIVEM MOVE A PARTIR DA POSIÇÃO IDA
                UI.printBoard(chessMatch.getPieces(), possibleMoves); // IMPRIMINDO TABUL COM SOBRECARGA PARA IMPRIMIR POSSIVEL MOVE COLORIDO
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                // EXECUTANDO MOVIMENTO DE XADREZ E RETORNAR POSSIVEL PEÇA CAPTURED..

                if (capturedPiece != null) { // SE PEÇA CAPTURADA FOR DIFERENTE DE NULL, SIGNIFICA Q ALGUMA PECA FOI CAPTURADA
                    captured.add(capturedPiece); // ACRESCENTA A PEÇA PARA LISTA DE CAPTURADAS
                }
           }
            catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.printMatch(chessMatch, captured); // MOSTRANDO PARTIDA FINALIZADA
    }
}
