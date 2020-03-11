package user;

import card.Deck;

public class Dealer {
    private Hands hands;

    public Dealer(Deck deck) {
        this.hands = new Hands(deck);
    }

    public int handSize() {
        return hands.size();
    }
}
