package boardgame;

public class Board { // CLASSE TABULEIRO

    private int rows;  //QUANTIDADE DE LINHAS DO TABULEIRO
    private int columns; // QUANTIDADE DE COLUNAS DO TABULEIRO
    private Piece[][] pieces;  // MATRIZ DE PEÇAS

    public Board(int rows, int columns) { // QUANDO CRIAR TABU LINHA E COLUM TEM Q TER AO MINIMO 1
        if (rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: There must be at least 1 row and 1 column");
        } // Exception PERSONALIZADA CASO DESEJA CRIAR TABUL SEM ROWS E COLUMN
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns]; // MATRIZ INSTANCIADA COM A QANTIDADE DE LINHAS E COLUNAS
    }
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    // METODO QUE RETORNO UM OBJETO DO TIPO PIECE / RECEBE ROW E COLUMN
    public Piece piece(int row, int column) {
        if (!positionExists(row, column)) { // SE A POSITION NAO EXISTE...
            throw new BoardException("Position not on the board");
        } // EXCEPTION PERSONAIADA / PROGRAMA DEFENSIVO
        return pieces[row][column];  // RETORNA A MATRIX PIECES NA LINHA ROW E COLUNA COLUMN
    }

    // SOBRECARGA DO METODO PIECE ACIMA
    public Piece piece(Position position) { // RETORNA PEÇA PELA POSIÇÃO
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) { // METODO QUE RECEBE UMA PEÇA E UMA POSIÇÃO
        if (thereIsAPiece(position)) { // SE EXISTE PEÇA NESTA POSIÇÃO, NAO PODE COLOCAR OUTRA
            throw new BoardException("There is already a piece on position " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece; // VAI NA MATRIX DE PEÇAS DO TABU, NA LINHA E COLUNA
        piece.position = position; // RECEBENDO POSITION DO METODO
    }

    private boolean positionExists(int row, int column) { // METODO AUXILIAR RECEBENDO LINHA E COLUMN
       return row >= 0 && row < rows && column >= 0 && column < columns; // TESTAR SE TERÁ PEÇA PELA LINHA E COLUMN DO QUE A POSIÇÃO ABAIXO...

    }

    public boolean positionExists(Position position) { // REAPROVEITANDO METODO ACIMA
        return positionExists(position.getRow(), position.getColumn());

    }

    public boolean thereIsAPiece(Position position) { // METODO PARA TESTAR SE EXISTE UMA POSIÇÃO NESTA POSITION
       if (!positionExists(position)) { // ANTES DE TESTAR O THEREIS ELE JA TESTA A POSITION SE EXISITE
           throw new BoardException("Position not on the board");
       }
       return piece(position) != null; // SE A PEÇA FOR DIFERENTE DE NULO, TEM UMA PEÇA NESTA POSITION
    }
}
