package blackjack.domain.game;

import blackjack.domain.card.CardRank;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Score implements Comparable<Score> {

    public static final int ACE_HIGH_VALUE = 11;
    public static final int DEALER_EXTRA_CARD_LIMIT = 16;
    public static final int BLACKJACK = 21;
    private static final String NEGATIVE_SCORE_NOT_ALLOWED_EXCEPTION = "점수는 음수가 될 수 없습니다!";

    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score valueOf(final int value) {
        validateNotNegative(value);
        return ScoreCache.getCache(value);
    }

    private static void validateNotNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(NEGATIVE_SCORE_NOT_ALLOWED_EXCEPTION);
        }
    }

    public Score add(Score anotherScore) {
        int addedValue = this.value + anotherScore.value;
        return ScoreCache.getCache(addedValue);
    }

    public Score incrementOneAce() {
        int replacedValue = this.value + CardRank.getAceValueDifference();
        return ScoreCache.getCache(replacedValue);
    }

    public boolean isBlackjackScore() {
        return this.value == BLACKJACK;
    }

    public boolean isBustScore() {
        return this.value > BLACKJACK;
    }

    public int toInt() {
        return value;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public String toString() {
        return "Score{" + "value=" + value + '}';
    }

    private static class ScoreCache {
        static Map<Integer, Score> cache = new HashMap<>();

        static Score getCache(final int value) {
            return Optional.ofNullable(cache.get(value))
                    .orElseGet(() -> createNewCache(value));
        }

        static Score createNewCache(final int value) {
            cache.put(value, new Score(value));
            return cache.get(value);
        }
    }
}
