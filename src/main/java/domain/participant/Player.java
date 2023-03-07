package domain.participant;

import domain.card.Card;
import java.util.List;

public final class Player extends Participant {

    public Player(final Name name, final List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean isHittable() {
        return calculateScore().isHittableForPlayer();
    }
}
