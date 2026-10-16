import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class BattleHistory {

    private ArrayList<String> results = new ArrayList<>();
    private Path historyPath = Path.of("BattleHistory.txt");

    public void load() {
        if (Files.exists(historyPath)) {
            try {
                results.addAll(Files.readAllLines(historyPath));
            } catch (IOException e) {
                System.out.println("Could not load battle history");
            }
        }
    }

    public void save() {
        try {
            Files.write(historyPath, results);
        } catch (IOException e) {
            System.out.println("Could not save battle history");
        }
    }

    public void addResult(String result) {
        results.add(result);
        save();
    }

    public void showHistory() {
        System.out.println("===== BATTLE HISTORY =====");

        if (results.isEmpty()) {
            System.out.println("No battles recorded yet.");
        } else {
            for (String result : results) {
                System.out.println(result);
            }
        }
    }
}