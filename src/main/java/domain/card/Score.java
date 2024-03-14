package domain.card;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private static final Map<Integer, Score> SCORE_POOL = new HashMap<>();
    private static final Score BLACKJACK_SCORE;
    private static final Score BONUS_SCORE;
    private static final int MIN_SCORE_VALUE = 1;
    private static final int MAX_SCORE_VALUE = 41;

    static {
        for (int i = MIN_SCORE_VALUE; i <= MAX_SCORE_VALUE; i++) {
            SCORE_POOL.put(i, new Score(i));
        }
        BLACKJACK_SCORE = SCORE_POOL.get(21);
        BONUS_SCORE = SCORE_POOL.get(10);
    }

    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score get(int value) {
        validateRange(value);
        return SCORE_POOL.get(value);
    }

    private static void validateRange(int value) {
        if (value < MIN_SCORE_VALUE || MAX_SCORE_VALUE < value) {
            throw new IllegalArgumentException(
                    String.format("rejected value: %d - 점수는 %d에서 %d 사이의 값이어야 합니다.",
                            value, MIN_SCORE_VALUE, MAX_SCORE_VALUE)
            );
        }
    }

    public Score plus(Score other) {
        return Score.get(this.value + other.value);
    }

    public Score plusBonusScore() {
        Score plussedBonusScore = plus(BONUS_SCORE);
        if (plussedBonusScore.isBustScore()) {
            return this;
        }
        return plussedBonusScore;
    }

    public boolean isBustScore() {
        return this.isGreaterThan(BLACKJACK_SCORE);
    }

    public boolean isBlackjackScore() {
        return this.equals(BLACKJACK_SCORE);
    }

    public boolean isGreaterThan(Score other) {
        return this.value > other.value;
    }

    public boolean isLessThan(Score other) {
        return this.value < other.value;
    }

    public int getValue() {
        return this.value;
    }
}
