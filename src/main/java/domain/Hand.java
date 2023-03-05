package domain;

import java.util.ArrayList;
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

        aceCount = countAce(aceCount);

        value = sumValue(value);

        value = chooseAceValue(value, aceCount);
        return value;
    }

    private int countAce(int aceCount) {
        for (Card card : hand) {
            aceCount = addAceCount(aceCount, card);
        }
        return aceCount;
    }

    private int addAceCount(int aceCount, Card card) {
        if (card.getValue() == HIGH_ACE_VALUE) {
            aceCount += 1;
        }
        return aceCount;
    }

    private int chooseAceValue(int value, int aceCount) {
        while (value > BUST_BOUNDARY_VALUE && aceCount > 0) {
            value -= HIGH_ACE_VALUE - LOW_ACE_VALUE;
            aceCount -= 1;
        }
        return value;
    }

    private int sumValue(int value) {
        for (Card card : hand) {
            value += card.getValue();
        }
        return value;
    }

    public List<String> getCards() {
        List<String> cards = new ArrayList<>();
        for (Card card : hand) {
            cards.add(card.getName());
        }
        return cards;
    }
}
