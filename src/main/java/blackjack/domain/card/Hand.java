package blackjack.domain.card;

import java.util.List;

public final class Hand {

    private static final int MAKE_MORE_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> hand;

    public Hand(final List<Card> hand) {
        this.hand = hand;
    }

    public int totalScore() {
        int score = preCalculate();

        if (canPlusScore(score)) {
            score += 10;
        }
        return score;
    }

    private int preCalculate() {
        return this.hand.stream()
                .mapToInt(Card::score)
                .sum();
    }

    private boolean canPlusScore(final int sum) {
        return containsAce() && sum <= BLACKJACK_SCORE - MAKE_MORE_SCORE;
    }

    private boolean containsAce() {
        return this.hand.stream()
                .anyMatch(Card::isAce);
    }
}
