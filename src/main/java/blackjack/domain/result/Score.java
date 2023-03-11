package blackjack.domain.result;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private static final int BLACK_JACK_VALUE = 21;
    private static final int ACE_OFFSET = -10;
    private static final Score BLACKJACK = new Score(BLACK_JACK_VALUE, ScoreStatus.BLACKJACK);
    private static final Map<Integer, Score> NON_BLACKJACK_SCORE_CACHE_MAP = new HashMap<>();

    private final ScoreStatus status;
    private final int value;

    private Score(final int value, final ScoreStatus status) {
        this.value = value;
        this.status = status;
    }

    public static Score from(final int value) {
        return generateScore(value);
    }

    public static Score calculateScore(int totalValue, int aceCount, int cardGroupSize) {
        if (totalValue == BLACK_JACK_VALUE && cardGroupSize == 2) {
            return BLACKJACK;
        }
        while (totalValue > BLACK_JACK_VALUE && aceCount > 0) {
            totalValue += ACE_OFFSET;
            aceCount--;
        }
        return generateScore(totalValue);
    }

    private static Score generateScore(final int totalValue) {
        if (totalValue > BLACK_JACK_VALUE) {
            return NON_BLACKJACK_SCORE_CACHE_MAP.computeIfAbsent(totalValue,
                    value -> new Score(value, ScoreStatus.BUST));
        }
        return NON_BLACKJACK_SCORE_CACHE_MAP.computeIfAbsent(totalValue,
                value -> new Score(value, ScoreStatus.DRAWABLE));
    }

    public int getValue() {
        return value;
    }

    public boolean isBust() {
        return status == ScoreStatus.BUST;
    }

    public boolean isDrawAble() {
        return status == ScoreStatus.DRAWABLE;
    }

    public boolean isBigger(final Score score) {
        return value > score.value;
    }

    public boolean isEqual(final Score score) {
        return value == score.value;
    }

    public boolean isBlackJack() {
        return status == ScoreStatus.BLACKJACK;
    }
}
