package blackjack.domain.participant;

import blackjack.domain.card.Hand;

public class Player extends Participant {

    public Player(Name NAme, Hand hand) {
        super(NAme, hand);
    }

    public Player(String name, Hand hand) {
        super(new Name(name), hand);
    }

    @Override
    public boolean canHit() {
        return !isBust();
    }
}
