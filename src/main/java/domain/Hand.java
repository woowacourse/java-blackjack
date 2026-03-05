package domain;

import domain.card.Cards;

public class Hand {
    public static final int BLACK_JACK = 21;
    private Cards cards;

    public Hand(Cards cards) {
        this.cards = cards;
    }

    public boolean isBust() {
        return score() > BLACK_JACK;
    }

    public int score() {
        int total = total();

        if (cards.hasAce()) {
            while (total > BLACK_JACK) {
                total -= 10;
            }
        }

        return total;
    }

    public int total() {
        return cards.total();
    }
}
