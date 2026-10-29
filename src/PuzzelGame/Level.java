package PuzzelGame;

public enum Level {
	EASY(3),
    HARD(4);

    private int size;

    Level(int size) {
        this.size = size;
    }

    public int getSize() { return size; }
}
