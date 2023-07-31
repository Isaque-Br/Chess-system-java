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
}
