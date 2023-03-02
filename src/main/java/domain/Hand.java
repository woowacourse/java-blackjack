package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int INIT_ACE_COUNT = 0;
    private static final int HIGH_ACE_VALUE = 11;
    private static final int LOW_ACE_VALUE = 1;
    private static final int BUST_BOUNDARY_VALUE = 21;
    private final List<Card> hand;
    private int aceCount;

    public Hand(List<Card> cards) {
        this.hand = cards;
        this.aceCount = INIT_ACE_COUNT;
    }

    public void addCard(Card card) {
        if (card.getValue() == HIGH_ACE_VALUE) {
            aceCount += 1;
        }
        hand.add(card);
    }

    public int calculateValue() {
        int value = 0;

        for (Card card : hand) {
            value += card.getValue();
        }

        if (value > BUST_BOUNDARY_VALUE && aceCount > INIT_ACE_COUNT) {
            value -= HIGH_ACE_VALUE - LOW_ACE_VALUE;
            aceCount -= 1;
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
