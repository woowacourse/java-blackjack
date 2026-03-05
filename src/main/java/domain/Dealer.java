package domain;

import domain.card.Card;

public class Dealer {
    private final Hand hand;

    public Dealer(){
        hand = new Hand();
    }

    public boolean isBurst() {
        return hand.isBurst();
    }

    public void draw(Card card){
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }
}
