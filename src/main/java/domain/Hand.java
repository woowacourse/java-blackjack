package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int HIGH_ACE_VALUE = 11;
    private static final int LOW_ACE_VALUE = 1;
    private static final int BUST_BOUNDARY_VALUE = 21;
    private final List<Card> hand;

    public Hand(List<Card> cards) {
        this.hand = cards;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int calculateValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            value += card.getValue();
            aceCount = countAce(aceCount, card);
        }
        value = exchangeAceLowValueWhenBust(value, aceCount);
        return value;
    }

    private int exchangeAceLowValueWhenBust(int value, final int aceCount) {
        while (value > BUST_BOUNDARY_VALUE && aceCount > 0) {
            value -= HIGH_ACE_VALUE - LOW_ACE_VALUE;
        }
        return value;
    }

    private static int countAce(int aceCount, final Card card) {
        if (card.isAce()) {
            aceCount += 1;
        }
        return aceCount;
    }

    public List<String> getCardNames() {
        List<String> cards = new ArrayList<>();
        for (Card card : hand) {
            cards.add(card.getName());
        }
        return cards;
    }

    public List<Card> getCards() {
        return new ArrayList<>(Collections.unmodifiableList(hand));
    }
}
