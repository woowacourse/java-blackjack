package blackjack.domain.player;

import blackjack.domain.card.Hand;
import blackjack.domain.card.Hit;

public final class Gambler extends Player {

    private Gambler(final Name name, final Hand hand) {
        super(name, hand);
    }

    public static Gambler create(final String name) {
        return new Gambler(Name.from(name), new Hit());
    }

    @Override
    public boolean isDrawable() {
        return hand().isDrawable();
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
