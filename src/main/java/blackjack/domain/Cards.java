package blackjack.domain;

import java.util.List;

public class Cards {

    public static final int MAKE_ACE_BIGGER_SCORE = 10;
    public static final int BURST_SCORE = 21;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateTotalScore() {
        int score = preCalculate();

        if (containsAce()) {
            score = plusScoreIfNotBurst(score);
        }

        return score;
    }

    private int preCalculate() {
        int score = 0;
        for (Card card : cards) {
            score += card.convertToScore();
        }

        return score;
    }

    private int plusScoreIfNotBurst(int score) {
        if (score + MAKE_ACE_BIGGER_SCORE <= BURST_SCORE) {
            score += MAKE_ACE_BIGGER_SCORE;
        }

        return score;
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
