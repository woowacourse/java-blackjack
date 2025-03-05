package domain;

import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int getSumOfRank() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean isBust() {
        return getSumOfRank() > 21;
    }

}
