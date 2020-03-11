package user;

import card.Deck;

public class Dealer {
    private Hands hands;

    public Dealer(Deck deck) {
        this.hands = new Hands(deck);
    }

    public Dealer(Hands hands) {
        this.hands = hands;
    }

    public int handSize() {
        return hands.size();
    }

    public void needMoreCard(Deck deck) {
        if (this.hands.score() <= 16) {
            this.hands.draw(deck);
        }
    }
}
