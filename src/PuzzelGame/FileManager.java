package PuzzelGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    private static final String FILE_NAME = "score.txt";

    public static void saveScore(int newScore) {
        int currentBest = loadBestScoreInt();

        if (currentBest == -1 || newScore < currentBest) {
            try {
                FileWriter writer = new FileWriter(FILE_NAME);
                writer.write("Best Moves: " + newScore);
                writer.close();
            } catch (IOException e) {
            }
        }
    }

    public static String loadScore() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return "No Saved Data";

            Scanner scanner = new Scanner(file);
            String data = "";
            while (scanner.hasNextLine()) {
                data += scanner.nextLine();
            }
            scanner.close();
            return data;
        } catch (Exception e) {
            return "No Saved Data";
        }
    }

    private static int loadBestScoreInt() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return -1;

            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                scanner.close();
                return Integer.parseInt(line.replaceAll("[^0-9]", ""));
            }
            scanner.close();
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }
}
