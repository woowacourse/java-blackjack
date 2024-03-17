package domain.participant;

import java.util.HashMap;
import java.util.Map;

public class Scores {
    private static final int MINIMUM_SCORE_VALUE = 1;
    private static final int MAXIMUM_SCORE_VALUE = 31;
    private static final Map<Integer, Score> value = Scores.init();

    private Scores() {
    }

    private static Map<Integer, Score> init() {
        final Map<Integer, Score> scores = new HashMap<>();
        for (int score = MINIMUM_SCORE_VALUE; score <= MAXIMUM_SCORE_VALUE; score++) {
            scores.put(score, new Score(score));
        }

        return scores;
    }

    public static Score getScore(int target) {
        return value.get(target);
    }
}
