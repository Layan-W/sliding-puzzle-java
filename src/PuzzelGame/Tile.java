package PuzzelGame;
public class Tile {

    private int value;
    private int correctRow;
    private int correctCol;
    private int row;
    private int col;

    public Tile(int value, int row, int col, int size) {
        this.value = value;
        this.row = row;
        this.col = col;

        if (value == 0) {
            this.correctRow = -1;
            this.correctCol = -1;
        } else {
            this.correctRow = (value - 1) / size;
            this.correctCol = (value - 1) % size;
        }
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public int getCorrectRow() { return correctRow; }
    public int getCorrectCol() { return correctCol; }
    public int getValue() { return value; }
}
