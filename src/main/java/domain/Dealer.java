package domain;

import domain.card.Card;

public class Dealer {

    private Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    public void receiveCard(Card card) {
        hand.receiveCard(card);
    }
}
