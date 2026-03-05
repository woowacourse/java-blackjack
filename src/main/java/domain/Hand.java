package domain;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int BLACK_JACK = 21;
    private List<Card> cards = new ArrayList<>();

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isBust() {
        return score() > BLACK_JACK;
    }

    public int score() {
        int total = total();

        if (hasAce()) {
            while (total > BLACK_JACK) {
                total -= 10;
            }
        }

        return total;
    }

    private boolean hasAce() {
        for (Card card : cards) {
            return card.isAce();
        }

        return false;
    }

    public int total() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }
}
