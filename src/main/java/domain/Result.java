package domain;

import java.util.HashMap;
import java.util.Map;

public class Result {

    public static Map<String, Boolean> calculateResult(Map<Participant, Integer> participantScores) {
        Map<String, Boolean> gameResult = new HashMap<>();

        int dealerScore = 0;
        for (Map.Entry<Participant, Integer> entry : participantScores.entrySet()) {
            if (entry.getKey().getName().equals("딜러")) {
                dealerScore = entry.getValue();
            }
        }

        for (Map.Entry<Participant, Integer> entry : participantScores.entrySet()) {
            String name = entry.getKey().getName();
            int score = entry.getValue();

            if (dealerScore < score) {
                gameResult.put(name, true);
                continue;
            }
            gameResult.put(name, false);
        }

        return gameResult;
    }

}
