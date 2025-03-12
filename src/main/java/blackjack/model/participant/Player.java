package blackjack.model.participant;

import java.util.ArrayList;

public class Player extends Participant {

    public Player(String name) {
        super(name, new ArrayList<>());
    }

    @Override
    public boolean canHit() {
        return !isBust() && !isBlackjack();
    }
}
