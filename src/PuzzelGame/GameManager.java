package PuzzelGame;

public class GameManager {

    private static GameManager instance;
    private Puzzle puzzle;
    private GameState gameState;
    private Level currentLevel;

    private GameManager() {}

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame(Level level) {
        this.currentLevel = level;

        int size = (level == Level.EASY) ? 3 : 4; // Level 2 = 4x4

        puzzle = new Puzzle(size);
        gameState = new GameState(level);
    }

    public boolean handleMove(String direction) {
        boolean moved = puzzle.moveTile(direction);
        if (moved) {
            gameState.addMove();
            if (puzzle.isGameWon()) {
                gameState.setWon();
            }
        }
        return moved;
    }

    public Tile[][] getBoard() {
        return puzzle.getBoard();
    }

    public GameState getGameState() {
        return gameState;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
