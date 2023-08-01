package application;

import chess.ChessPiece;

public class UI {

    // CRIANDO O METODO PRINTBOARD RECEBENDO MATRIX CHESSPIECE COM NOME DE PIECES
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i=0; i<pieces.length; i++) { // FOR QUE NUMERA AS LINHAS
            System.out.print((8 - i) + " ");
            for (int j=0; j<pieces.length; j++) {
                printPiece(pieces[i][j]); // PARA IMPRIMIR AS PEÇAS
            }
            System.out.println(); // QUEBRA DE LINHA
        }
        System.out.println("  A B C D E F G H");

    }

    // METODO AUXILIAR PARA IMPRIMIR UMA PEÇA
    private static void printPiece(ChessPiece piece) { // RECEBENDO UMA PEÇA
        if (piece == null) { // SE A PEÇA FOR IGUAL A NULO, SIGNIFICA QUE NÃO TINHA PEÇA NO TABULEIRO
            System.out.print("-"); //...E IMPRIMI UM -
        }
        else { // CASO CONTRARIO, IMPRIMI A PEÇA
            System.out.print(piece);
        }
        System.out.print(" "); // PARA AS PEÇAS NÃO FICAR GRUDADAS
    }
}
