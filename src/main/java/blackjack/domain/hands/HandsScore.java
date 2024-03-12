package blackjack.domain.hands;

import java.util.Objects;

public class HandsScore {
    private static final int BLACK_JACK = 21;
    private static final int ACE_UPGRADE_SCORE = 10;
    private final int handsScore;

    public HandsScore(int score) {
        this.handsScore = score;
    }

    public static HandsScore upgradeAceWhenNotBust(int totalScore) {
        if (totalScore + ACE_UPGRADE_SCORE <= BLACK_JACK) {
            return new HandsScore(totalScore + ACE_UPGRADE_SCORE);
        }
        return new HandsScore(totalScore);
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

    public int toInt() {
        return handsScore;
    }
}
