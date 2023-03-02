package model.user;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class Hand {
    private static final int BURST_NUMBER = 21;

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

    public int calculateTotalValue() {
        if (getTotalValue() > BURST_NUMBER && isIncludeAce()) {
            return getTotalValue() - 10;
        }
        return getTotalValue();
    }

    private int getTotalValue() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private boolean isIncludeAce() {
        return this.cards.stream()
                .anyMatch(Card::isAce);
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect(joining(", "));
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
}
