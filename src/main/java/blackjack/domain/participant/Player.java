package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public class Player extends Participant {

    private static final int HIT_STANDARD = 21;

    public Player(Name name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean isHittable() {
        return calculateScore() < HIT_STANDARD;
    }
}
