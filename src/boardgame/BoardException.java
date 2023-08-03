package boardgame;

public class BoardException extends RuntimeException { // PARA SER OPÇÃO DE SER TRATADA
    private static final long seriaLVersionUID = 1L;

    public BoardException(String msg) {
        super(msg);
    }
}
