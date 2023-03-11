package blackjack.domain.player;

import blackjack.domain.card.Hand;

public final class Dealer extends AbstractPlayer {
    private static final int SCORE_LOWER_BOUND = 16;

    private Dealer(final Name name, final Hand hand) {
        super(name, hand);
    }

    public static Dealer create() {
        return new Dealer(Name.createDealerName(), new Hand());
    }

    @Override
    public boolean isDrawable() {
        return hand().isPlayable() && hand().score() <= SCORE_LOWER_BOUND;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    public int getCardCount() {
        return hand().getSymbols().size();
    }
}
