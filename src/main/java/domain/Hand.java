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
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }
}
