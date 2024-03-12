package domain.card;

import java.util.Objects;

public final class Score {

    private static final Score BLACKJACK_SCORE = new Score(21);
    private static final Score ACE_SPECIAL_SCORE = new Score(10);
    private static final int MINIMUM_SCORE = 0;

    private final int value;

    public Score(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < MINIMUM_SCORE) {
            throw new IllegalArgumentException(String.format("[ERROR] 점수는 %d점 이상이어야 합니다.", MINIMUM_SCORE));
        }
    }

    public static Score totalScoreOf(ParticipantCards cards) {
        Score totalScore = initialSum(cards);
        if (cards.hasAce()) {
            totalScore = totalScore.addAceSpecialScore(totalScore);
        }
        return totalScore;
    }

    private static Score initialSum(ParticipantCards cards) {
        return cards.getCards()
            .stream()
            .map(Card::score)
            .reduce(new Score(MINIMUM_SCORE), Score::add);
    }

    private Score addAceSpecialScore(Score previousScore) {
        if (previousScore.add(ACE_SPECIAL_SCORE).isNotBust()) {
            return previousScore.add(ACE_SPECIAL_SCORE);
        }
        return previousScore;
    }

    public Score add(Score previousScore) {
        return new Score(value + previousScore.value);
    }

    public boolean isBust() {
        return this.isGreaterThan(BLACKJACK_SCORE);
    }

    public boolean isNotBust() {
        return !this.isGreaterThan(BLACKJACK_SCORE);
    }

    public boolean isGreaterThan(Score other) {
        return value > other.value;
    }

    public boolean isLessThan(Score other) {
        return value < other.value;
    }

    public int toInt() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Score) obj;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Score[" +
            "value=" + value + ']';
    }
}
