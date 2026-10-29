package PuzzelGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Puzzle implements Movable {

    private Tile[][] board;
    private int size;
    private int emptyRow;
    private int emptyCol;

    public Puzzle(int size) {
        this.size = size;
        board = new Tile[size][size];
        initializeBoard();
    }

    public Puzzle(Level level) {
        this.size = level.getSize();
        board = new Tile[size][size];
        initializeBoard();
    }
    
    //Builds the board in order and sets the empty tile, then shuffles it.
    private void initializeBoard() {
        board = new Tile[size][size];

        int value = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size - 1 && j == size - 1) {
                    board[i][j] = new Tile(0, i, j,size);
                    emptyRow = i;
                    emptyCol = j;
                } else {
                    board[i][j] = new Tile(value, i, j,size);
                    value++;
                }
            }
        }

        shuffle();
    }

    //Checks if the move direction is allowed.  
    @Override
    public boolean isMoveValid(String direction) {
        switch (direction) {
            case "UP":    return emptyRow < size - 1;
            case "DOWN":  return emptyRow > 0;
            case "LEFT":  return emptyCol < size - 1;
            case "RIGHT": return emptyCol > 0;
            default:      return false;
        }
    }
    //Moves a tile into the empty space
    @Override
    public boolean moveTile(String direction) {
        if (!isMoveValid(direction)) return false;

        int newRow = emptyRow;
        int newCol = emptyCol;

        switch (direction) {
            case "UP":    newRow = emptyRow + 1; break;
            case "DOWN":  newRow = emptyRow - 1; break;
            case "LEFT":  newCol = emptyCol + 1; break;
            case "RIGHT": newCol = emptyCol - 1; break;
        }

        Tile temp = board[emptyRow][emptyCol];
        board[emptyRow][emptyCol] = board[newRow][newCol];
        board[newRow][newCol]     = temp;

        emptyRow = newRow;
        emptyCol = newCol;
        return true;
    }
    //Checks if all tiles are in their correct positions.  
    public boolean isGameWon() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Tile t = board[r][c];
                if (!t.isEmpty()) {
                    if (t.getCorrectRow() != r ||
                        t.getCorrectCol() != c)
                        return false;
                }
            }
        }
        return true;
    }
    
   // Randomly shuffles tiles until the board becomes solvable.
    private void shuffle() {
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < size * size; i++) {
            values.add(i);
        }

        do {
            Collections.shuffle(values);
        } while (!isSolvable(values));

        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = values.get(index++);
                board[i][j] = new Tile(value, i, j,size);

                if (value == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }
    }
    
    //Determines if the shuffled tile arrangement is solvable.
    private boolean isSolvable(List<Integer> values) {
        int inversions = 0;

        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                if (values.get(i) != 0 && values.get(j) != 0 && values.get(i) > values.get(j)) {
                    inversions++;
                }
            }
        }

        if (size % 2 == 1) {
            return inversions % 2 == 0; // 3x3
        } else {
            int emptyRowFromBottom = size - emptyRow;
            if (emptyRowFromBottom % 2 == 0) {
                return inversions % 2 == 1;
            } else {
                return inversions % 2 == 0;
            }
        }
    }

    //Getters
    public Tile[][] getBoard() { return board; }
    public int      getSize()  { return size; }
}
