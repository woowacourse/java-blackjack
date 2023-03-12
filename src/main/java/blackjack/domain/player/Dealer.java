package blackjack.domain.player;

import blackjack.domain.card.Hand;
import blackjack.domain.card.Hit;

public final class Dealer extends Player {
    private static final int SCORE_LOWER_BOUND = 16;

    private Dealer(final Name name, final Hand hand) {
        super(name, hand);
    }

    public static Dealer create() {
        return new Dealer(Name.createDealerName(), new Hit());
    }

    @Override
    public boolean isDrawable() {
        return hand().isDrawable() && hand().score() <= SCORE_LOWER_BOUND;
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
