package domain.participant;

import domain.card.Card;
import java.util.List;

public final class Player extends Participant {

    private static final int BUST_BOUNDARY_EXCLUSIVE = 21;

    public Player(final Name name, final List<Card> cards) {
        super(name, cards);
    }

    public boolean canReceive() {
        return this.calculateScore() < BUST_BOUNDARY_EXCLUSIVE;
    }
}
