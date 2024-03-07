package model.participant;

public class Player extends Participant {

    public Player(Name name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return !cardDeck.isBust();
    }
}
