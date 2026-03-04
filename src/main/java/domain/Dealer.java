package domain;

import domain.card.Card;

public class Dealer {
    private Hand hand;

    public void draw(Card card){
        hand.addCard(card);
    }
}
