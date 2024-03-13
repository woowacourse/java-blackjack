package blackjack.domain.hands;

import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class HandsScore {
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 31;
    private static final Map<Integer, HandsScore> CACHE_SCORES = IntStream.rangeClosed(MIN_SCORE, MAX_SCORE)
            .boxed()
            .collect(toMap(it -> it, HandsScore::new));
    private static final int BLACK_JACK = 21;
    private static final int ACE_UPGRADE_SCORE = 10;
    private final int handsScore;

    private HandsScore(int score) {
        this.handsScore = score;
    }

    public static HandsScore from(int score) {
        return CACHE_SCORES.get(score);
    }

    public static HandsScore upgradeAceWhenNotBust(int totalScore) {
        if (totalScore + ACE_UPGRADE_SCORE <= BLACK_JACK) {
            return new HandsScore(totalScore + ACE_UPGRADE_SCORE);
        }
        return new HandsScore(totalScore);
    }

    public static HandsScore blackJack() {
        return HandsScore.from(BLACK_JACK);
    }

    public boolean isHigherThan(HandsScore score) {
        return handsScore > score.handsScore;
    }

    public boolean isSame(HandsScore score) {
        return handsScore == score.handsScore;
    }

    public boolean isLowerThan(HandsScore score) {
        return handsScore < score.handsScore;
    }

    public int toInt() {
        return handsScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandsScore that = (HandsScore) o;
        return handsScore == that.handsScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(handsScore);
    }
}
