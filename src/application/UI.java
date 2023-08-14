package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    /* https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }*/

    public static ChessPosition readChessPosition(Scanner sc) { //METODO PARA LER A POSIÇÃO
        try {
            String s = sc.nextLine();
            char column = s.charAt(0); // PARA LER A LETRA
            int row = Integer.parseInt(s.substring(1)); // PARA LER O NUMERO DA LINHA
            return new ChessPosition(column, row);
        }
        catch (RuntimeException e) { // QUALQUER RunTimeException QUE ACONTECER DE FORMATO OU OURA COISA..
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
        } // EXCEPTION DE ERRO DE ENTRADA DE DADOS
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) { // IMPRIINDO PARTIDA
        printBoard(chessMatch.getPieces()); // IMPRIMI O TABUL
        System.out.println(); // QUEBRA DE LINHA
        printCapturedPieces(captured); // DEPOIS DE IMPRIMIR O TABULEIRO, IMPRIME LISTA DE PEÇAS CAPTURADAS
        System.out.println("Turn : " + chessMatch.getTurn()); // IMPRIME O TURNO
        System.out.println("Waiting player: " + chessMatch.getCurrentPlayer()); // ESPERANDO JOGADOR ATUAL JOGAR
        if (chessMatch.getCheck()) {
            System.out.println("CHECK!");
        }
    }

    // CRIANDO O METODO PRINTBOARD RECEBENDO MATRIX CHESSPIECE COM NOME DE PIECES
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i=0; i<pieces.length; i++) { // FOR QUE NUMERA AS LINHAS
            System.out.print((8 - i) + " ");
            for (int j=0; j<pieces.length; j++) {
                printPiece(pieces[i][j], false); // PARA IMPRIMIR AS PEÇAS
            } // QUANDO FOR IMPRIMIR O TABUL SEM OS MOVE POSSIVEL, INDICANDO QUE NENHUMA PEÇA É PRA TER DUNDO COLORIDO
            System.out.println(); // QUEBRA DE LINHA
        }
        System.out.println("  A B C D E F G H");

    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i=0; i<pieces.length; i++) { // FOR QUE NUMERA AS LINHAS
            System.out.print((8 - i) + " ");
            for (int j=0; j<pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]); // PARA IMPRIMIR AS PEÇAS
            } //METODO QUE VAI OU NAO COLORIR O FUNDO DEPENDENDO DA VARIAVEL background, CONSIDERANDO OS MOVE POSSIVEL
            System.out.println(); // QUEBRA DE LINHA
        }
        System.out.println("  A B C D E F G H");

    }

    // METODO AUXILIAR PARA IMPRIMIR UMA PEÇA
    private static void printPiece(ChessPiece piece, boolean background) { // RECEBENDO UMA PEÇA
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) { // SE A PEÇA FOR IGUAL A NULO, SIGNIFICA QUE NÃO TINHA PEÇA NO TABULEIRO
            System.out.print("-" + ANSI_RESET); //...E IMPRIMI UM -
        }
        else { // CASO CONTRARIO, IMPRIMI A PEÇA
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_RED + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_PURPLE + piece + ANSI_RESET);
            }
        }
        System.out.print(" "); // PARA AS PEÇAS NÃO FICAR GRUDADAS
    }

    // METODO QUE RECEBE UMA LISTA DE PECAS DE XADREZ QUE SERÁ RESPONSAVEL POR IMPRIMIR PEÇAS CAPTURADAS
    private static void printCapturedPieces(List<ChessPiece> captured) {
        List<ChessPiece> white  = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList()); // OPERAÇÃO PARA FILTRAR A LIST
        List<ChessPiece> black  = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
        // LIST DE PEÇAS CAPTURES + COLOR + FILTRAGM DE LIST(CONCEITO EXPRESSAO LAMBDA) + PREDICATO PEGA X.GET COLOR(ELEMENTO DA LISTA)
        System.out.println("Capture pieces:");
        System.out.print("White: ");
        System.out.print(ANSI_GREEN); // PARA GARANTIR QUE A LISTA IMPRESSA SEJA A COR BRANCA
        System.out.println(Arrays.toString(white.toArray())); //IMPRIMINDO ARRAYS DE VALOR
        System.out.print(ANSI_RESET); // RESETAR COR DA IMPRESSÃO
        System.out.print("Black: ");
        System.out.print(ANSI_YELLOW); // PARA GARANTIR QUE A LISTA IMPRESSA SEJA A COR BRANCA
        System.out.println(Arrays.toString(black.toArray())); //IMPRIMINDO ARRAYS DE VALOR
        System.out.print(ANSI_RESET); // RESETAR COR DA IMPRESSÃO
    }
}
