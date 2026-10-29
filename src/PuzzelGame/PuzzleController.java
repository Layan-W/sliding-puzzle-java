package PuzzelGame;

public class PuzzleController {

    private PuzzleView view;
    private TimerManager timer;
    private int moves;
    private GameManager manager;

    public PuzzleController(PuzzleView view) {
        this.view = view;
        this.timer = new TimerManager();
        this.moves = 0;
        this.manager = GameManager.getInstance();

        manager.startGame(Level.EASY);
        view.setController(this);

        timer.startTimer();
        updateBoard();

        view.updateTitle("LEVEL 1 | Moves: 0 | Time: 0s");
    }

    public void handleMove(String direction) {
        boolean moved = manager.handleMove(direction);
        updateGame(moved);
    }

    public void handleTileClick(int row, int col) {}

    private void updateGame(boolean moved) {
        if (moved) {
            moves = manager.getGameState().getMoves();
            updateBoard();

            view.updateTitle(
                "LEVEL " +
                (manager.getGameState().getLevel() == Level.EASY ? "1" : "2 - HARD") +
                " | Moves: " + moves +
                " | Time: " + timer.getSeconds() + "s"
            );

         
            if (manager.getGameState().isWon()) {
                timer.stopTimer();

       
                if (manager.getGameState().getLevel() == Level.EASY) {

                    view.showMessage("You Win Level 1! Moving to Level 2...");

                    manager.startGame(Level.HARD);
                    moves = 0;
                    timer.resetTimer();
                    timer.startTimer();
                    updateBoard();
                    view.updateTitle("LEVEL 2 - HARD | Moves: 0 | Time: 0s");

                    return;
                }

                //
                view.showMessage(
                    "YOU WIN LEVEL 2!\n" +
                    "Moves: " + moves + "\n" +
                    "Time: " + timer.getSeconds() + " seconds"
                );
            }
        }
    }

    public void restartGame() {
        manager.startGame(Level.EASY);
        moves = 0;
        timer.resetTimer();
        timer.startTimer();
        updateBoard();
        view.updateTitle("SLIDING PUZZLE - LEVEL 1");
    }

    private void updateBoard() {
        view.displayBoard(manager.getBoard());
    }
}
