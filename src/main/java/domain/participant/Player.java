package domain.participant;

import domain.Name;

public class Player extends Participant {

    public Player(Name name) {
        super(name);
    }

    @Override
    boolean isReceivable() {
        return score() <= BLACKJACK_SCORE;
    }
}
