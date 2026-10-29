package PuzzelGame;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PuzzleView extends VBox  {

    private GridPane grid = new GridPane();
    private Button upBtn;
    private Button downBtn;
    private Button leftBtn;
    private Button rightBtn;
    private Button restartBtn;
    private Label titleLabel;
    private PuzzleController controller;

    public PuzzleView() {
        setSpacing(25);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #121214; -fx-padding: 30;");

        titleLabel = new Label("SLIDING PUZZLE - LEVEL 1");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: 900; -fx-text-fill: #FFFFFF; -fx-letter-spacing: 2px;");

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setStyle("-fx-background-color: #1A1A1E; -fx-padding: 15; -fx-background-radius: 15;");

        upBtn = new Button("↑");
        downBtn = new Button("↓");
        leftBtn = new Button("←");
        rightBtn = new Button("→");

        String arrowStyle = "-fx-background-color: #2A2A32; -fx-text-fill: #A0A0AA; -fx-font-size: 18; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand;";
        upBtn.setStyle(arrowStyle); 
        downBtn.setStyle(arrowStyle); 
        leftBtn.setStyle(arrowStyle); 
        rightBtn.setStyle(arrowStyle);

        upBtn.setPrefSize(60, 40); 
        downBtn.setPrefSize(60, 40); 
        leftBtn.setPrefSize(60, 40); 
        rightBtn.setPrefSize(60, 40);

        restartBtn = new Button("RESTART GAME");
        restartBtn.setPrefWidth(200);
        restartBtn.setPrefHeight(45);
        restartBtn.setStyle("-fx-background-color: #00ADB5; -fx-text-fill: #121214; -fx-font-size: 14; -fx-font-weight: bold; -fx-background-radius: 25; -fx-cursor: hand;");

        HBox bottomArrows = new HBox(12);
        bottomArrows.setAlignment(Pos.CENTER);
        bottomArrows.getChildren().addAll(leftBtn, downBtn, rightBtn);

        VBox arrowsBox = new VBox(12);
        arrowsBox.setAlignment(Pos.CENTER);
        arrowsBox.getChildren().addAll(upBtn, bottomArrows);

        getChildren().addAll(titleLabel, grid, arrowsBox, restartBtn);
    }

    public void setController(PuzzleController controller) {
        this.controller = controller;
        upBtn.setOnAction(e -> controller.handleMove("UP"));
        downBtn.setOnAction(e -> controller.handleMove("DOWN"));
        leftBtn.setOnAction(e -> controller.handleMove("LEFT"));
        rightBtn.setOnAction(e -> controller.handleMove("RIGHT"));
        restartBtn.setOnAction(e -> controller.restartGame());
    }

    public void updateTitle(String text) {
        titleLabel.setText(text);
    }

    public void displayBoard(Tile[][] board) {
        grid.getChildren().clear();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                Button tile = new Button();

                if (board[i][j].isEmpty()) {
                    tile.setText("");
                    tile.setStyle("-fx-background-color: #1A1A1E; -fx-background-radius: 10;");
                } else {
                    tile.setText(String.valueOf(board[i][j].getValue()));
                    tile.setStyle(
                            "-fx-background-color: #222831; " +
                            "-fx-text-fill: #00ADB5; " +
                            "-fx-font-size: 20; " +
                            "-fx-font-weight: 800; " +
                            "-fx-background-radius: 10; " +
                            "-fx-cursor: hand;"
                    );
                }

                tile.setPrefSize(70, 70);
                grid.add(tile, j, i);
            }
        }
    }

    public void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}
