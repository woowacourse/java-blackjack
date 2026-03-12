package blackjack.model;

import java.util.List;

public class Player extends Participant {

    public Player(Name name, Hand hand) {
        super(name, hand);
    }

    public Player(String name, Hand hand) {
        super(new Name(name), hand);
    }

    @Override
    public List<Card> getInitialVisibleCards() {
        return getCards();
    }

    public boolean canHit() {
        return !getScore().isBust();
    }
}
