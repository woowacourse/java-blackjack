package domain;

import java.util.List;

public class Dealer {
    private final Hand hand;

    private Dealer(Hand hand) {
        this.hand = hand;
    }

    public static Dealer from(Hand hand) {
        return new Dealer(hand);
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean checkThreshold() {
        return hand.getScore().getValue() <= 16;
    }

    public void addHandCard(Card card) {
        hand.addCard(card);
    }
}
