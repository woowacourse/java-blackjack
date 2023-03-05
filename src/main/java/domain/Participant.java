package domain;

import java.util.List;

public class Participant {
    private static final int BUST_BOUNDARY_VALUE = 21;

    private final Hand hand;
    private final String name;

    public Participant(List<Card> cards, String name) {
        this.hand = new Hand(cards);
        this.name = name;
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public int getHandValue() {
        return hand.calculateValue();
    }

    public List<String> getCardNames() {
        return hand.getCardNames();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public String getName() {
        return name;
    }

    boolean isBust() {
        return hand.calculateValue() > BUST_BOUNDARY_VALUE;
    }
}
