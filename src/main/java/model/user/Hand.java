package model.user;

import model.card.Card;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

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
        int totalValue = sumCardsValue();
        return calculateCardsValue(totalValue);
    }

    private int calculateCardsValue(int totalValue) {
        if (totalValue > BUST_NUMBER && isIncludeAce()) {
            return totalValue - 10;
        }
        return totalValue;
    }

    private int sumCardsValue() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private boolean isIncludeAce() {
        return this.cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return this.cards;
    }

}
