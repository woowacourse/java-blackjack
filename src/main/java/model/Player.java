package model;

import java.util.List;

public class Player extends User {
    public Player(String name, Deck deck, int initialDrawCount) {
        super(name, deck, initialDrawCount);
    }

    public Player(String name, List<Card> cards){
        super(name, cards);
    }
}
