package domain.participant;

import domain.card.Hand;

public class Player extends Participant {

    public Player(final Name name, final Hand hand) {
        super(name, hand);
    }


    public boolean canDraw() {
        return !isBust() && !isBlackjack();
    }
}
