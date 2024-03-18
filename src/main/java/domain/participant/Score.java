package domain.participant;

import java.util.HashMap;
import java.util.Map;

public record Score(int value) {
    private static final int MINIMUM_SCORE_VALUE = 1;
    private static final int MAXIMUM_SCORE_VALUE = 31;
    private static final Map<Integer, Score> cached = init();

    private static Map<Integer, Score> init() {
        final Map<Integer, Score> scores = new HashMap<>();
        for (int score = MINIMUM_SCORE_VALUE; score <= MAXIMUM_SCORE_VALUE; score++) {
            scores.put(score, new Score(score));
        }

        return scores;
    }

    public static Score valueOf(final int value) {
        if (!cached.containsKey(value)) {
            return new Score(value);
        }

        return cached.get(value);
    }

    public boolean isBigger(final Score other) {
        return this.value > other.value;
    }
}

