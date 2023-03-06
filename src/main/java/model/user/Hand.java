package model.user;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {

    private static final int BUST_NUMBER = 21;
    private static final int ACE_REVISE_VALUE = 10;

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
        return reviseTotalValue(totalValue);
    }

    private int reviseTotalValue(int totalValue) {
        int aceCount = countAce();
        while (totalValue > BUST_NUMBER && aceCount > 0) {
            aceCount--;
            totalValue -= ACE_REVISE_VALUE;
        }
        return totalValue;
    }

    private int calculateTotalValue() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
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
