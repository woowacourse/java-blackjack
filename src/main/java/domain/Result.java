package domain;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private static final String DEALER = "딜러";
    private static final Integer JUDGE_SCORE = 21;

    public static Map<String, Boolean> calculateResult(Map<Participant, Integer> participantScores) {
        Map<String, Boolean> gameResult = new HashMap<>();

        int dealerScore = getDealerScore(participantScores);

        for (Map.Entry<Participant, Integer> entry : participantScores.entrySet()) {
            if (entry.getKey().getName().equals(DEALER)) {
                continue;
            }
            String name = entry.getKey().getName();
            int score = entry.getValue();

            if (score > JUDGE_SCORE) {
                gameResult.put(name, false);
                continue;
            }

            if (dealerScore > JUDGE_SCORE) {
                gameResult.put(name, true);
                continue;
            }

            if (dealerScore < score) {
                gameResult.put(name, true);
                continue;
            }
            gameResult.put(name, false);
        }

        return gameResult;
    }

    private static int getDealerScore(Map<Participant, Integer> participantScores) {
        int dealerScore = 0;
        for (Map.Entry<Participant, Integer> entry : participantScores.entrySet()) {
            if (entry.getKey().getName().equals(DEALER)) {
                dealerScore = entry.getValue();
            }
        }
        return dealerScore;
    }

}
