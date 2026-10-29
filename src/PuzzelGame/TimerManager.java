package PuzzelGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimerManager {

    private int seconds;
    private Timeline timeline;

    public TimerManager() {
        seconds = 0;

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    seconds++;
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void startTimer() {
        timeline.play();
    }

    public void stopTimer() {
        timeline.stop();
    }

    public void resetTimer() {
        seconds = 0;
    }

    public int getSeconds() {
        return seconds;
    }
}
