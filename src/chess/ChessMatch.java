package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
                            //  ONDE TEM AS REGRAS DO JOGO DE XADREZ
public class ChessMatch {  // CLASSE PARTIDA DE XADREZ / CORAÇÃO DO SISTEMA XADREZ

    private int turn;
    private Color currentPlayer;
    private Board board; // ASSOCIAÇÃO COM TABULEIRO
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable; // PEÇA VULNERAVEL AO ENPASSANT
    private ChessPiece promoted; // PEÇA PROMOVIDA PROPERTY

    private List<Piece> piecesOnTheBoard = new ArrayList<>(); // LISTA DO BOARD INSTANCIADA QUANDO OBJETO ChessMatch FOR CRIADO / PODE COLOCAR NO CONATRUTOR TBM
    private List<Piece> capturedPieces = new ArrayList<>(); // LISTA DE PEÇAS CAPTURADAS


    // CONSTRUTOR QUE PRECISA DIZER O TAMANHO DO JOGO DE XADREZ - NESTA CLASS
    public ChessMatch() {
        board = new Board(8, 8); // CRIANDO O TABULEIRO 8 POR 8 E...INICIANDO SETUP
        turn = 1;
        currentPlayer = Color.YELLOW;
        check = false; // SÓ PRA ENFATIZAR O FALSE
        initialSetup(); // CHAMANDO O METODO DE INICIAR A PARTIDA NO CONSTRUTOR DA PARTIDA
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() { // METODO GETCHECK PARA TER ACESSO NO PROGRAMA PRINCIPL
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public ChessPiece getEnPassantVulnerable() {
        return enPassantVulnerable;
    }

    public ChessPiece getPromoted() { // METODO GET PARA TER ACESSO NO PROGRAMA PRINCIPAL
        return promoted;
    }

    // METODO QUE RETORNA UMA MATRIX DE PEÇAS DE XADREZ - CORRESPONDENTE A ESSA PARTIDA
    public ChessPiece[][] getPieces() { // O PROGRAMA SÓ VAI PODER ENXERGAR A PEÇA DE XADREZ E NÃO A PEÇA INTERNA DO TABULEIRO
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; // LIBERA PARA O PROGRAMA UMA MATRIX DO TIPO CHESSPIECE
        for (int i = 0; i < board.getRows(); i++) { // FOR PRA PERCORRER AS LINHAS DA MATRIX
            for (int j = 0; j < board.getColumns(); j++) { // FOR PRA PERCORRER AS COLUNAS DA MATRIX
                mat[i][j] = (ChessPiece) board.piece(i, j); // FAZENDO DOWNCASTING PRA RECEBER A PEÇA DE XADREZ
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position); // VALIDA POSIÇÃO DE ORIGEM DEPOIS QUE O USUARIO ENTRAR COM ELA
        return board.piece(position).possibleMoves(); // RETORNA OS MOVIMENTOS POSSIVEIS DA PEÇA DESSA POSIÇÃO
    }

    // METODO QUE PEGA A PEÇA DA POSIÇÃO DE ORIGEM E A LEVA PARA A POSIÇÃO DE DESTINO
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition(); // CONVERTENDO AS DUAS POSIÇÃO PARA POSIÇÃO DA MATRIX
        Position target = targetPosition.toPosition();
        validateSourcePosition(source); // VALIDANDO SE HAVIA UMA PEÇA NA POSIÇÃO DE ORIGEM, SE NÃO EXISITIR VAI LANÇAR EXCEPTION
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target); // POSITION DE ORIGEM E DESTINO OPERAÇÃO QUE REALIZA O MOVIMENTO DA PEÇA

        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        ChessPiece movedPiece = (ChessPiece)board.piece(target); // DOWNCASTING PRA RECEBER A PEÇA DE XADREZ

        // // # SPECIAL MOVE PROMOTION / PROMOÇÃO DE PEÃO
        promoted = null;
        if (movedPiece instanceof Pawn) { // SE A PEÇA MOVIDA FOR UM PEÃO
            if (movedPiece.getColor() == Color.YELLOW && target.getRow() == 0 || movedPiece.getColor() == Color.BLUE && target.getRow() == 7) { // SE A PEÇA MOVIDA FOR AMARELA E CHEGAR NA LINHA 0 OU SE A PEÇA MOVIDA FOR AZUL E CHEGAR NA LINHA 7
                promoted = (ChessPiece)board.piece(target); // PROMOVIDA RECEBE A PEÇA QUE FOI MOVIDA
                promoted = replacePromotedPiece("Q"); // PROMOVIDA RECEBE A PEÇA QUE FOI MOVIDA E SUBSTITUI PELA PEÇA QUE O USUARIO DIGITAR
            }
        }

        // # SPECIAL MOVE PROMOTION / PROMOÇÃO DE PEÃO
         // INICIALIZANDO A PEÇA PROMOVIDA COM NULL PRA ASSEGURA QUE É UM NOVO TESTE

        // SE TESTCHECK DO OPONENT DO CURRENTPLAYER SE TRUE, PARTIDA EM CHECK SENAO: NAO ESTA EM CHECK
        check = (testCheck(opponent(currentPlayer))) ? true : false;

        if (testCheckMate(opponent(currentPlayer))) { // TESTA SE A JOGADA QUE FEZ DEIXOU  O CHECKMATE P/ OPONENT DO CURRENTPLAYER ACABOU...
            checkMate = true;
        }
        else {

            nextTurn(); // PARA TROCAR O TURNO
        }

        // Special move en passant / SE A PEÇA MOVIDA FOI UM PEÃO ELA MOVEU DUAS CASAS DE 2 PRA + OU PRA - / = MOVIMENTO INICIAL DE 2 CASAS
        if (movedPiece instanceof Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2 )) {
            enPassantVulnerable = movedPiece; // RECEBE PEÇA REMOVIDA / PEAO VULNERAVEL AO TOMAR /
        }
        else {
            enPassantVulnerable = null; // NAO TEM NGM INVUNERAL PRA TOMAR ENPASSANT
        }

            return (ChessPiece) capturedPiece; // DOWNCASTING DA PEÇA CAPTURADA PARA TIPO PIECE
    }

    public ChessPiece replacePromotedPiece(String type) { // METODO QUE RECEBE O TIPO DA PEÇA QUE O USUARIO DIGITAR
        if (promoted == null) { // SE A PEÇA PROMOVIDA FOR NULL
            throw new IllegalStateException("There is no piece to be promoted"); // LANÇA EXCEÇÃO
        }
        if (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) { // SE O USUARIO DIGITAR ALGO DIFERENTE DESSAS LETRAS
            return promoted; // RETORNA A PEÇA PROMOVIDA
        }

        Position pos = promoted.getChessPosition().toPosition(); // PEGANDO A POSIÇÃO DA PEÇA PROMOVIDA
        Piece p = board.removePiece(pos); // REMOVENDO A PEÇA DA POSIÇÃO
        piecesOnTheBoard.remove(p); // REMOVENDO A PEÇA DA LISTA DE PEÇAS DO TABULEIRO

        ChessPiece newPiece = newPiece(type, promoted.getColor()); // CRIANDO UMA NOVA PEÇA COM O TIPO QUE O USUARIO DIGITAR E A COR DA PEÇA PROMOVIDA
        board.placePiece(newPiece, pos); // COLOCANDO A NOVA PEÇA NA POSIÇÃO QUE A PEÇA PROMOVIDA ESTAVA
        piecesOnTheBoard.add(newPiece); // ADICIONANDO A NOVA PEÇA NA LISTA DE PEÇAS DO TABULEIRO

        return newPiece; // RETORNANDO A NOVA PEÇA
    }

    // METODO AUXILIAR QUE RECEBE A PEÇA QUE FOI MOVIDA E A POSIÇÃO DE ORIGEM E DESTINO
    private ChessPiece newPiece(String type, Color color) { // METODO QUE RECEBE O TIPO DA PEÇA E A COR
        if (type.equals("B")) return new Bishop(board, color); // SE O TIPO FOR BISPO RETORNA UM BISPO
        if (type.equals("N")) return new Knight(board, color); // SE O TIPO FOR CAVALO RETORNA UM CAVALO
        if (type.equals("Q")) return new Queen(board, color); // SE O TIPO FOR RAINHA RETORNA UMA RAINHA
        return new Rook(board, color); // SE O TIPO FOR TORRE RETORNA UMA TORRE
    }

    private Piece makeMove(Position source, Position target) { // METODO QUE MOVIMENTA DE DESTINO ORIGEM PARA DESTINO
        ChessPiece p = (ChessPiece) board.removePiece(source); // REMOVE PIECE DA ORIGEM
        p.increaseMoveCount(); // QUANDO FOR MOVER A PEÇA INCREMENTA O MOVIMENTO DELA
        Piece capturedPiece = board.removePiece(target); // REMOVE POSSIVEL PEÇA QUE ESTEJA NA POSIÇÃO DE DESTINO
        board.placePiece(p, target); // COLOCANDO PEÇA QUE ESTAVA NA ORIGEM NO DESTINO

        if (capturedPiece != null) { // SE A PEÇA CAPTURADA != D NULL...
            piecesOnTheBoard.remove(capturedPiece); // REMOVE PECA DO TABUL
            capturedPieces.add(capturedPiece); // E ADCIONA NA LIST DE PEÇAS CAPTURADAS
        }

        // # SPECIAL MOVE CASTLING KINGSIDE ROOK // TRATANDO O ROQUE PEQUENO
        if (p instanceof King && target.getColumn() == source.getColumn() + 2) { // SE A PEÇA FOR UMA INSTANCIA DE KING E A COLUNA DE DESTINO FOR IGUAL A COLUNA DE ORIGEM + 2
            Position sourceT = new Position(source.getRow(), source.getColumn() + 3); // CRIANDO UMA POSIÇÃO
            Position targetT = new Position(source.getRow(), source.getColumn() + 1); // CRIANDO UMA POSIÇÃO DE DESTINO DA TORRE
            ChessPiece rook = (ChessPiece) board.removePiece(sourceT); // REMOVENDO A TORRE DA POSIÇÃO
            board.placePiece(rook, targetT); // COLOCANDO A TORRE NA POSIÇÃO DE DESTINO
            rook.increaseMoveCount(); // INCREMENTANDO O MOVIMENTO DA TORRE
        }

        // # SPECIAL MOVE CASTLING QUEENSIDE ROOK // TRATANDO O ROQUE GRANDE
        if (p instanceof King && target.getColumn() == source.getColumn() - 2) { // SE A PEÇA FOR UMA INSTANCIA DE KING E A COLUNA DE DESTINO FOR IGUAL A COLUNA DE ORIGEM - 2
            Position sourceT = new Position(source.getRow(), source.getColumn() - 4); // CRIANDO UMA POSIÇÃO DE ORIGEM DA TORRE
            Position targetT = new Position(source.getRow(), source.getColumn() - 1); // CRIANDO UMA POSIÇÃO DE DESTINO DA TORRE
            ChessPiece rook = (ChessPiece) board.removePiece(sourceT); // REMOVENDO A TORRE DA POSIÇÃO
            board.placePiece(rook, targetT); // COLOCANDO A TORRE NA POSIÇÃO DE DESTINO
            rook.increaseMoveCount(); // INCREMENTANDO A QUANTIDADE DE MOVIMENTOS DA TORRE
        }

        // # Specialmove en passant
        if (p instanceof Pawn) { //  PAWN ANDOU NA DIAGONAL E NÃO CAPTUROU PIECE SIGNIFICA QUE ROLOU EN PASSANT
            if (source.getColumn() != target.getColumn() && capturedPiece == null) {
                Position pawnPosition;
                if (p.getColor() == Color.YELLOW) { // SE A COR DA PIECE QUE MOVEU FOR = A YELLOW, SIGNIFICA QUE A PIECE CAPTURED ESTA EM BAIXO DO PEAO YELLOW
                    pawnPosition = new Position(target.getRow() + 1, target.getColumn()); // POSIÇÃO DO pawnPosition vai ser new Posiion na mesma posição de destino com linha/row + 1
                }
                else { // SENAO FOR UMA PIECE BLUE A SER CAPTURED, VAI SER UMA ROW ACIMA = -1
                    pawnPosition = new Position(target.getRow() - 1, target.getColumn());
                }
                capturedPiece = board.removePiece(pawnPosition); // REMOVENDO O PEAO EN PASSANT DO TABULEIRO
                capturedPieces.add(capturedPiece); // ADC PEAO NA LISTT DE PIECES CAPTURED
                piecesOnTheBoard.remove(capturedPiece); // REMOVENDO A PIEE CAPTURED DO TABULEIRO
            }
        }

        return capturedPiece;
    }

    // METODO PARA DESFAZER MOVIMENTO, RECEBENDO POSIÇÃO DE ORIGEM, DE TARGET E UMA POSSIVEL PEÇA CAPTURADA
    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece) board.removePiece(target); // TIRANDO PEÇA QUE MOVEU PARA DESTINO
        p.decreaseMoveCount(); //  DECREMENTANDO O MOVIMENTO DA PEÇA
        board.placePiece(p, source);// COLOCANDO PEÇA DE VOLTA NA POSIÇÃO DE ORIGEM

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target); // VOLTANDO PEÇA CAPTURED PARA POSIÇÃO DE DESTINO
            capturedPieces.remove(capturedPiece); // TIRANDO A PEÇA DE LIST DE PEÇA CAPTURED
            piecesOnTheBoard.add(capturedPiece); // ADD NA LIST DE PEÇA DO TABULEIRO
        }

        // # PROCESSO INVERSO DO CASTLING, DESFAZ O ROQUE PEQUENO
        // # SPECIAL MOVE CASTLING KINGSIDE ROOK // TRATANDO O ROQUE PEQUENO
        if (p instanceof King && target.getColumn() == source.getColumn() + 2) { // SE A PEÇA FOR UMA INSTANCIA DE KING E A COLUNA DE DESTINO FOR IGUAL A COLUNA DE ORIGEM + 2
            Position sourceT = new Position(source.getRow(), source.getColumn() + 3); // CRIANDO UMA POSIÇÃO
            Position targetT = new Position(source.getRow(), source.getColumn() + 1); // CRIANDO UMA POSIÇÃO DE DESTINO DA TORRE
            ChessPiece rook = (ChessPiece) board.removePiece(targetT); // REMOVENDO A TORRE DA POSIÇÃO DE DESTINO
            board.placePiece(rook, sourceT); // COLOCANDO A TORRE NA POSIÇÃO DE DESTINO
            rook.increaseMoveCount(); // INCREMENTANDO O MOVIMENTO DA TORRE
        }

        // # SPECIAL MOVE CASTLING QUEENSIDE ROOK // TRATANDO O ROQUE GRANDE
        if (p instanceof King && target.getColumn() == source.getColumn() - 2) { // SE A PEÇA FOR UMA INSTANCIA DE KING E A COLUNA DE DESTINO FOR IGUAL A COLUNA DE ORIGEM - 2 - ROQUE GRANDE
            Position sourceT = new Position(source.getRow(), source.getColumn() - 4); // CRIANDO UMA POSIÇÃO DE ORIGEM DA TORRE
            Position targetT = new Position(source.getRow(), source.getColumn() - 1); // CRIANDO UMA POSIÇÃO DE DESTINO DA TORRE
            ChessPiece rook = (ChessPiece) board.removePiece(targetT); // REMOVENDO A TORRE DA POSIÇÃO
            board.placePiece(rook, sourceT); // COLOCANDO A TORRE NA POSIÇÃO DE DESTINO
            rook.increaseMoveCount(); // INCREMENTANDO A QUANTIDADE DE MOVIMENTOS DA TORRE
        }

        // # Specialmove en passant
        if (p instanceof Pawn) { //  DESFAZENDO A JOGADA - A PEAO MOVEU NA DIAGONAL CAPTURED FOI A PEÇA VULNERAVEL EN PASSANT ?
            if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
                ChessPiece pawn = (ChessPiece)board.removePiece(target); // TIRANDO DO LUGAR ERRADO
                Position pawnPosition;
                if (p.getColor() == Color.YELLOW) { // SE A COR DA PIECE QUE MOVEU FOR = A YELLOW, VAI TER QUE DEVOLVER PRA LINHA 3
                    pawnPosition = new Position(3, target.getColumn()); // POSIÇÃO DO pawnPosition vai ser new Posiion na mesma posição de destino com linha/row + 1
                }
                else { // SENAO, FOR UMA PIECE BLUE A SER CAPTURED, VAI TER QUE DEVOLVER PARA LINHA 4
                    pawnPosition = new Position(4, target.getColumn());
                }
                board.placePiece(pawn, pawnPosition); // COLOCANDO A PEÇA DO LUGAR ERRADO NA POSIÇÃO QUE DECIDI
            }
        }
    }

    private void validateSourcePosition(Position position) { // VALIDAÇÃO DA POSIÇÃO DE ORIGEM
        if (!board.thereIsAPiece(position)) { // SE NÃO EXISTIR UMA PEÇA NESTA POSIÇÃO, TERÁ UMA EXCEPTION
            throw new ChessException("There is no piece on source position");
        } // if PARA TESTAR SE EXISTE MOVIMENTO POSSIVEL PARA A PEÇA IR

        // SE JOGADOR ATUAL != DA PEÇA NO BOARD .getColor QUE É PROPIEDADE DO ChessPiece ENTAO, FAREMOS DOWNCASTING
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) { // EXCEPTION SE TENTAR MOVER PEÇA ADVERSARIA
            throw new ChessException("The chosen piece is not yours");
        }
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

    private void nextTurn() { // METODO TROCA DE TURNO
        turn++; // IMPLEMENTANDO - TURNO 1 QUE PASSA PARA O TURNO 2
        currentPlayer = (currentPlayer == Color.YELLOW) ? Color.BLUE : Color.YELLOW;
    }  //(CONDICIONAL TERNARIA:  SE O JOGADOR ATUAL == COLOR.YELLOW ? ENTAO ELE VAI TER QUE SER O BLUE CASO : CONTRARO ELE VAI SER O COLOR.WHTE

    private Color opponent(Color color) {
        return (color == Color.YELLOW) ? Color.BLUE : Color.YELLOW;
    }

    // METODO PARA LOCALIZAR UM KING DE UMA DETERMINADA COR
    private ChessPiece king(Color color) { // FORMA PADRAO DE SE FILTRAR UMA LIST COM EXPRESSÃO LAMBDA
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) { // PRA CADA PEÇA P NA LIST ...
            if (p instanceof King) { // SE A PEÇA P FOR INSTANCIA DE KING...
                return (ChessPiece) p; // SIGNIFICA QUE ENCONTROU O REI
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        for (Piece p : opponentPieces) { // PARA CADA PEÇA p NA LISTA: DE PEÇAS DO OPONENT...TESTAREI MOV POSSIVEL
            boolean[][] mat = p.possibleMoves(); //
            // MATRIX DE MOVIMENTOS POSSIVEIS DESSA PE.CA ADVERSARIA p
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]) { // SE NESSA MATRIX NA LINHA KINGPOSI E COLUMNPOSI..
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color) { // TESTAR SE O REI ESTA EM CHECKMATE
        if (!testCheck(color)) { // SE ESSA COR NAO TIVER EM CHECK
            return false;       // SIGNIFICA QUE TB NAO ESTA EM CHECKMATE
        } // LISTA PEGANDO TODAS AS PEÇAS DO TABULEIRO E FILTRA COLOR DO PARAMETRO
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) { // FOR PARA PERCORRER TODAS AS PEÇAS DA LIST
            boolean[][] mat = p.possibleMoves(); // MATRIX PARA PEGAR P.POSSIBLEMOVE
            for (int i=0; i< board.getRows(); i++) { // PERCORRENDO AS LINHAS DA MATRIX
                for (int j=0; j< board.getColumns(); j++) { // PERCORRENDO AS COLUNAS DA MATRIXs
                    if (mat[i][j]) { // ESSA POSIÇÃO [I] [J] DA MATRIX, É UM MOVIMENTO POSSIVEL?
                        Position source = ((ChessPiece)p).getChessPosition().toPosition(); // TRUE, PEÇA P(CHESSPIECE(OBJETO), + GetChessPosition
                        Position target = new Position(i, j); // TARGET LEVARÁ PARA POSITION MAT[I][J], QUE É UM MOVIMENTO POSSIVEL
                        Piece capturedPiece = makeMove(source, target); //SOURCE LEVANDO PARA TARGET
                        boolean testCheck = testCheck(color); // TESTANDO SE AINDA ESTÁ EM CHECK COM O MÉTODO TESTCHECK/ SE AINDA ESTÁ EM CHECK
                        undoMove(source, target, capturedPiece); //CHAMANDO UNDOMOVE PARA DESFAZER O MOVIMENTO
                        if (!testCheck) { // SE NAO ESTAVA EM CHECK...TIRA O KING DO CHECK == FALSE
                            return false;
                        }
                    }
                }
            }
        }
        return true;

    }

    // METODO DE COLOCAR PEÇAS PASSANDO A POSIÇÃO NAS CORDENADAS DO XADREZ
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition()); // COLOCANDO PEÇA NO TABULEIRO
        piecesOnTheBoard.add(piece); // JA ADCIONA A PEÇA NA LISTA DE PEÇAS DO TABULEIRO
    }

    // METODO RESPONSAVEL POR INICIAR A PARTIDA DE XADREZ COLOCANDO AS PEÇAS NO TABUL
    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.YELLOW));
        placeNewPiece('b', 1, new Knight(board, Color.YELLOW));
        placeNewPiece('c', 1, new Bishop(board, Color.YELLOW));
        placeNewPiece('d', 1, new Queen(board, Color.YELLOW));


        placeNewPiece('e', 1, new King(board, Color.YELLOW, this));
        placeNewPiece('f', 1, new Bishop(board, Color.YELLOW));
        placeNewPiece('g', 1, new Knight(board, Color.YELLOW));
        placeNewPiece('h', 1, new Rook(board, Color.YELLOW));
        placeNewPiece('a', 2, new Pawn(board, Color.YELLOW, this));
        placeNewPiece('b', 2, new Pawn(board, Color.YELLOW, this));
        placeNewPiece('c', 2, new Pawn(board, Color.YELLOW, this));
        placeNewPiece('d', 2, new Pawn(board, Color.YELLOW, this));
        placeNewPiece('e', 2, new Pawn(board, Color.YELLOW, this));
        placeNewPiece('f', 2, new Pawn(board, Color.YELLOW, this));
        placeNewPiece('g', 2, new Pawn(board, Color.YELLOW, this));
        placeNewPiece('h', 2, new Pawn(board, Color.YELLOW, this));

        placeNewPiece('a', 8, new Rook(board, Color.BLUE));
        placeNewPiece('b', 8, new Knight(board, Color.BLUE));
        placeNewPiece('c', 8, new Bishop(board, Color.BLUE));
        placeNewPiece('d', 8, new Queen(board, Color.BLUE));
        placeNewPiece('e', 8, new King(board, Color.BLUE, this));
        placeNewPiece('f', 8, new Bishop(board, Color.BLUE));
        placeNewPiece('g', 8, new Knight(board, Color.BLUE));
        placeNewPiece('h', 8, new Rook(board, Color.BLUE));
        placeNewPiece('a', 7, new Pawn(board, Color.BLUE, this));
        placeNewPiece('b', 7, new Pawn(board, Color.BLUE, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLUE, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BLUE, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLUE, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLUE, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLUE, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLUE, this));
    }
}
