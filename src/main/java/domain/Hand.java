package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> hand;
    private int aceCount;

    public Hand(List<Card> cards) {
        this.hand = cards;
        this.aceCount = 0;
    }

    public void addCard(Card card) {
        if (card.getValue() == 11) {
            aceCount += 1;
        }
        hand.add(card);
    }

    public int calculateValue() {
        int value = 0;
        for (Card card : hand) {
            value += card.getValue();
        }

        if (value > 21 && aceCount > 0) {
            value -= 10;
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
