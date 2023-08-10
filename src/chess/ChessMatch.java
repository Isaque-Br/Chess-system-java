package chess;

import boardgame.Board;
import boardgame.Piece;
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

    // METODO QUE PEGA A PEÇA DEPOSIÇÃO DE ORIGEM E A LEVA PARA A POSIÇÃO DE DESTINO
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition(); // CONVERTENDO AS DUAS POSIÇÃO PARA POSIÇÃO DA MATRIX
        Position target = targetPosition.toPosition();
        validateSourcePosition(source); // VALIDANDO SE HAVIA UMA PEÇA NA POSIÇÃO DE ORIGEM, SE NÃO EXISITIR VAI LANÇAR EXCEPTION
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target); // POSITION DE ORIGEM E DESTINO OPERAÇÃO QUE REALIZA O MOVIMENTO DA PEÇA
        return (ChessPiece) capturedPiece; // DOWNCASTING DA PEÇA CAPTURADA PARA TIPO PIECE
    }

    private Piece makeMove(Position source, Position target) { // METODO QUE MOVIMENTA DE DESTINO ORIGEM PARA DESTINO
        Piece p = board.removePiece(source); // REMOVE PIECE DA ORIGEM
        Piece capturedPiece = board.removePiece(target); // REMOVE POSSIVEL PE.CA QUE ESTEJA NA POSIÇÃO DE DESTINO
        board.placePiece(p, target); // COLOCANDO PEÇA QUE ESTAVA NA ORIGEM NO DESTINO
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) { // VALIDAÇÃO DA POSIÇÃO DE ORIGEM
        if (!board.thereIsAPiece(position)) { // SE NÃO EXISTIR UMA PEÇA NESTA POSIÇÃO, TERÁ UMA EXCEPTION
            throw new ChessException("There is no piece on source position");
        } // if PARA TESTAR SE EXISTE MOVIMENTO POSSIVEL PARA A PEÇA IR
        if (!board.piece(position).isThereAnyPossibleMove()) { // SE NÃO TIVER NENHUM MOVIMENTO POSSIVEL..
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    // VALIDA SE A POSIÇÃO DE DESTINO  É VALIDA EM RELAÇÃO A POSIÇÃO DE DESTINO
    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) { // SE PRA PEÇA DE ORIGEM A POSIÇÃO DE DESTINO NÃO É UM MOVIMENTO POSSIVEL...
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    // METODO DE COLOCAR PEÇAS PASSANDO A ∏ØÍˆCÃO NAS CORDENADAS DO XADREZ
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    // METODO RESPONSAVEL POR INICIAR A PARTIDA DE XADREZ COLOCANDO AS PEÇAS NO TABUL
    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
