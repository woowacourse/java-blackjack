package domain;

import domain.card.Card;

public class Player {
    private final String name;
    private final Hand hand;

    public Player(String name){
        this.name = name;
        hand = new Hand();
    }

    public void draw(Card card){
        hand.addCard(card);
    }

    public boolean isBurst() {
        return hand.isBurst();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
