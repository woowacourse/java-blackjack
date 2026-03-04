package domain;

import domain.card.Card;

public class Player {
    private final String name;
    private Hand hand;

    public Player(String name){
        this.name = name;
    }

    public void draw(Card card){
        hand.addCard(card);
    }

    public String getName() {
        return name;
    }
}
