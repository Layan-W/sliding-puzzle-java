package PuzzelGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        GameManager.getInstance().startGame(Level.EASY);

        PuzzleView view = new PuzzleView();
        new PuzzleController(view);

        Scene scene = new Scene(view, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Sliding Puzzle");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
