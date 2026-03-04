package domain;

import domain.card.Card;

public class Player {

    private Name name;
    private Hand hand;

    public Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void receiveCard(Card card) {
        hand.receiveCard(card);
    }

    public Hand getHand() {
        return hand;
    }
}
