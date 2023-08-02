package boardgame;

public class Board { // CLASSE TABULEIRO

    private int rows;  //QUANTIDADE DE LINHAS DO TABULEIRO
    private int columns; // QUANTIDADE DE COLUNAS DO TABULEIRO
    private Piece[][] pieces;  // MATRIZ DE PEÇAS

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns]; // MATRIZ INSTANCIADA COM A QANTIDADE DE LINHAS E COLUNAS
    }
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    // METODO QUE RETORNO UM OBJETO DO TIPO PIECE / RECEBE ROW E COLUMN
    public Piece piece(int row, int column) {
        return pieces[row][column];  // RETORNA A MATRIX PIECES NA LINHA ROW E COLUNA COLUMN
    }

    // SOBRECARGA DO METODO PIECE ACIMA
    public Piece piece(Position position) { // RETORNA PEÇA PELA POSIÇÃO
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) { // METODO QUE RECEBE UMA PEÇA E UMA POSIÇÃO
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position; // RECEBENDO POSITION DO METODO
    }
}
