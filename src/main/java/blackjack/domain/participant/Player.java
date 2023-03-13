package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public final class Player extends Participant {

    private Player(final Name name, final List<Card> cards) {
        super(name, cards);
    }

    public static Player of(final Name name, final List<Card> cards) {
        return new Player(name, cards);
    }

    @Override
    public boolean isHit() {
        return getScore().canHit();
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
