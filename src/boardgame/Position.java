package boardgame; // PACOTE CAMADA DO TABULEIRO

public class Position { // CLASSE QUE REPRESENTA UMA POSIÇÃO NO TABULEIRO
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setValues(int row, int column) { // OPERAÇÃO PARA ATUALIZAR OS VALORES DE UMA POSIÇÃO
        this.row = row;
        this.column = column;
    }

    @Override // toString PARA IMPRIMIR POSIÇÃO NA TELA
    public String toString() {
        return row + ", " + column;
    }
}

/* NESTA AULA CRIAMOS A CLASS POSITION. E OS TOPICS DE OOP QUE VIMOS FORAM:
- ENCAPSULAMENTO DOS CAMPOS COMO Private E COLOCANDO GET E SET.
- CONSTRUTOR COM PARAMETROS
- toString (Objeto / Overring) QUE APLICA O CONCEITO DE OBJETO QUE É UMA SUPERCLASS DE TODAS AS CLASSES
- SOBRECARGA DE CONSTRUTORES (CONSTRUTORES COM MESMO NOME, MAS SOBRESCREVENDO COM DIFERENTES ARGUMENTOS)
 */