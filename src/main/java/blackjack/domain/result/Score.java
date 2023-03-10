package blackjack.domain.result;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private static final Map<Integer, Score> SCORE_CACHE_MAP = new HashMap<>();
    private static final int BLACK_JACK_VALUE = 21;
    private static final int ACE_OFFSET = -10;

    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score from(final int value) {
        return SCORE_CACHE_MAP.computeIfAbsent(value, Score::new);
    }

    public static Score calculateScore(int totalValue, int aceCount) {
        while (totalValue > BLACK_JACK_VALUE && aceCount > 0) {
            totalValue += ACE_OFFSET;
            aceCount--;
        }
        return SCORE_CACHE_MAP.computeIfAbsent(totalValue, Score::new);
    }

    public int getValue() {
        return value;
    }

    public boolean isBust() {
        return value > BLACK_JACK_VALUE;
    }

    public boolean isBlackJackScore() {
        return value == BLACK_JACK_VALUE;
    }

    public boolean isBigger(final Score score) {
        return value > score.value;
    }

    public boolean isEqual(final Score score) {
        return value == score.value;
    }
}
