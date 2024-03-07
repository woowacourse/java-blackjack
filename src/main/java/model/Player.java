package model;

public class Player extends Participant {

    public Player(Name name, CardDeck cardDeck) {
        super(name, cardDeck);
    }

    @Override
    public boolean canHit() {
        return !cardDeck.isBust();
    }
}
