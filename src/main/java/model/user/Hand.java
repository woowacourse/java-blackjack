package model.user;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {

    private static final int BUST_NUMBER = 21;

    private final List<Card> cards;

    private Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public static Hand create() {
        return new Hand(new ArrayList<>());
    }

    public void receiveCard(final Card card) {
        this.cards.add(card);
    }

    public int getTotalValue() {
        int totalValue = calculateTotalValue();

        if (totalValue > BUST_NUMBER && isIncludeAce()) {
            return totalValue - 10;
        }

        return totalValue;
    }

    private int calculateTotalValue() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private boolean isIncludeAce() {
        return this.cards.stream()
                .anyMatch(Card::isAce);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
