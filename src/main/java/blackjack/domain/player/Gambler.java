package blackjack.domain.player;

import blackjack.domain.card.Hand;

public final class Gambler extends Player {

    private Gambler(final Name name, final Hand hand) {
        super(name, hand);
    }

    public static Gambler create(final String name) {
        return new Gambler(Name.from(name), new Hand());
    }

    @Override
    public boolean isDrawable() {
        return hand().isPlayable();
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
