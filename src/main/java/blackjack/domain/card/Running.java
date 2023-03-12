package blackjack.domain.card;

import blackjack.domain.player.Result;

public abstract class Running extends AbstractHand {
    Running(final Cards cards) {
        super(cards);
    }

    @Override
    public final boolean isDrawable() {
        return true;
    }

    @Override
    public final Result play(final Hand other) {
        throw new UnsupportedOperationException();
    }
}
