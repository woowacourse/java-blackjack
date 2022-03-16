package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Score implements Comparable<Score> {
    public static final int DEALER_HIT_THRESHOLD = 16;
    public static final int BLACKJACK = 21;

    private static final int VALUE_FOR_ADJUST_ACE_VALUE_TO_SMALL = 10;

    private final int value;

    // TODO: 음수 값에 대한 유효성 검증
    private Score(final int value) {
        this.value = value;
    }

    public static Score valueOf(final int value) {
        return ScoreCache.getCache(value);
    }

    public static Score calculateSumFrom(Hand hand) {
        List<Card> cards = hand.getCards();

        int maximumScore = cards.stream()
                .mapToInt(card -> card.getRankValue().getValue())
                .sum();

        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        return calculateScoreIncludingAce(maximumScore, aceCount);
    }

    private static Score calculateScoreIncludingAce(int maximumScore, int aceCount) {
        int adjustedScore = maximumScore;

        for (int i = 0; i < aceCount; i++) {
            if (adjustedScore <= Score.BLACKJACK) {
                break; // TODO: 2 depth 수정하기
            }
            adjustedScore -= VALUE_FOR_ADJUST_ACE_VALUE_TO_SMALL;
        }

        return Score.valueOf(adjustedScore);
    }

    public Score add(Score anotherScore) {
        int addedValue = this.value + anotherScore.value;
        return ScoreCache.getCache(addedValue);
    }

    public boolean isBusted() {
        return this.value > BLACKJACK;
    }

    public boolean isOverDealerHitThreshold() {
        return this.value > DEALER_HIT_THRESHOLD;
    }

    public int getValue() {
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

    // 메모: (앨런 리뷰) 멀티쓰레드 환경에서는 한번에 N건의 put이 일어날 수도 있습니다.
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
