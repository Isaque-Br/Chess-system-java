package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {  // CLASSE PARTIDA DE XADREZ / CORAÇÃO DO SISTEMA XADREZ

    private Board board;

    // CONSTRUTOR QUE PRECISA DIZER O TAMANHO DO JOGO DE XADREZ - NESTA CLASS
    public ChessMatch() {
        board = new Board(8, 8); // CRIANDO O TABULEIRO 8 POR 8 E...INICIANDO SETUP
        initialSetup(); // CHAMANDO O METODO DE INICIAR A PARTIDA NO CONSTRUTOR DA PARTIDA
    }

    // METODO QUE RETORNA UMA MATRIX DE PEÇAS DE XADREZ - CORRESPONDENTE A ESSA PARTIDA
    public ChessPiece[][] getPieces() { // O PROGRAMA SÓ VAI PODER ENXERGAR A PEÇA DE XADREZ E NÃO A PEÇA INTERNA DO TABULEIRO
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; // LIBERA PARA O PROGRAMA UMA MATRIX DO TIPO CHESSPIECE
        for (int i = 0; i < board.getRows(); i++) { // FOR PRA PERCORRER A MATRIX
            for (int j = 0; j < board.getColumns(); j++) { // FOR PRA PERCORRER AS COLUNAS DA MATRIX
                mat[i][j] = (ChessPiece) board.piece(i, j); // FAZENDO DOWNCASTING PRA RECEBER A PEÇA DE XADREZ
            }
        }
        return mat;
    }

    // METODO RESPONSAVEL POR INICIAR A PARTIDA DE XADREZ
    private void initialSetup() {
        board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
        board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
        board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
    }
}
