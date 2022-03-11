package blackjack.domain.game;

import blackjack.domain.card.CardRank;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Score implements Comparable<Score> {

    public static final int ACE_HIGH_VALUE = 11;
    public static final int DEALER_EXTRA_CARD_LIMIT = 16;
    public static final int BLACKJACK = 21;

    private final int value;

    // TODO: 음수 값에 대한 유효성 검증
    private Score(final int value) {
        this.value = value;
    }

    public static Score valueOf(final int value) {
        return ScoreCache.getCache(value);
    }

    public Score add(Score anotherScore) {
        int addedValue = this.value + anotherScore.value;
        return ScoreCache.getCache(addedValue);
    }

    public Score incrementOneAce() {
        int replacedValue = this.value + CardRank.getAceValueDifference();
        return ScoreCache.getCache(replacedValue);
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
