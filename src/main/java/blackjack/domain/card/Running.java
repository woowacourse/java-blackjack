package blackjack.domain.card;

public abstract class Running extends AbstractHand {
    protected Running(final Cards cards) {
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
