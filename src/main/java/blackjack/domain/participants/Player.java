package blackjack.domain.participants;

import blackjack.domain.card.Hand;

public class Player extends Participant {

    public Player(Name name, Hand hand) {
        super(name, hand);
    }

    public Player(String name, Hand hand) {
        super(new Name(name), hand);
    }

    @Override
    public boolean canHit() {
        return !isBust();
    }
}
