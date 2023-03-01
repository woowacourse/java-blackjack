package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;

    public Hand(List<Card> cards) {
        this.hand = cards;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int calculateValue() {
        int value = 0;

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
