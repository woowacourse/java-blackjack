package domain;

import java.util.List;

public class Player extends Gamer {
    private final Name name;
    //private final Hand hand;

    public Player(final Name name) {
        this.name = name;
        //this.hand = new Hand();
    }

    @Override
    public void hit(final Decks decks) {
        hand.add(decks.draw());
    }

    public Name getName() {
        return name;
    }
}
