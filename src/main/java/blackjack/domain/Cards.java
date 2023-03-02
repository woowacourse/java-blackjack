package blackjack.domain;

import java.util.List;

public class Cards {

    private static final int MAXIMUM_SCORE = 21;
    private static final int ACE_BONUS = 10;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateTotalScore() {
        final int score = getTotalScore();

        if (isExistAce() && isScoreUpdatable(score)) {
            return score + ACE_BONUS;
        }

        return score;
    }

    private int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean isExistAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean isScoreUpdatable(final int score) {
        return score + ACE_BONUS <= MAXIMUM_SCORE;
    }

    public boolean isTotalScoreOver() {
        return calculateTotalScore() > MAXIMUM_SCORE;
    }

    public boolean isMaximumScore() {
        return calculateTotalScore() == MAXIMUM_SCORE;
    }
}
