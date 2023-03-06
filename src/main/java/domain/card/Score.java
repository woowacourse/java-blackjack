package domain.card;

import java.util.Arrays;
import java.util.Objects;

public final class Score {

    private static final int SOFT_ADD = 10;
    private static final int SOFT_CONDITION = 21 - SOFT_ADD;

    private int score;
    private boolean hasAce;

    private Score(final int score, final boolean hasAce) {
        this.score = score;
        this.hasAce = hasAce;
    }

    public static Score from(final int score) {
        return new Score(score, false);
    }

    public void sumScore(Card... cards) {
        hasAce(cards);
        score = Arrays.stream(cards)
                .mapToInt(Card::getScore)
                .sum();
    }

    private void hasAce(Card... cards) {
        if (!hasAce) {
            hasAce = Arrays.stream(cards)
                    .anyMatch(this::isAce);
        }
    }

    private boolean isAce(Card card) {
        return card.getRank() == Rank.ACE;
    }

    private static boolean isHard(final int sum) {
        return sum <= SOFT_CONDITION;
    }

    public int getScore() {
        if (hasAce && isHard(score)) {
            return score + SOFT_ADD;
        }
        return score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score1 = (Score) o;
        return score == score1.score && hasAce == score1.hasAce;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, hasAce);
    }
}
