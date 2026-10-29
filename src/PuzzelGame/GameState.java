package PuzzelGame;

public class GameState {

	private int moves;
    private boolean won;
    private Level level;

    public GameState(Level level) {
        this.level = level;
        moves= 0;
        won = false;
    }

    public void addMove() { moves++; }
    public void setWon()  { won = true; }

    public void reset() {
        moves = 0;
        won   = false;
    }

    public int getMoves() { return moves; }
    public boolean isWon()    { return won; }
    public Level getLevel() { return level; }
	
	
	
}
