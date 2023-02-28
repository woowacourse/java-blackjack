package blackjack.domain;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateTotalScore() {
        int score = 0;

        for (Card card : cards) {
            score += card.convertToScore();
        }

        if (containsAce()) {
            if (score + 10 <= 21) {
                score += 10;
            }
        }
        return score;
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
