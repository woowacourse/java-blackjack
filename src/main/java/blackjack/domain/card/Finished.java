package blackjack.domain.card;

public abstract class Finished extends AbstractHand {
    Finished(final Cards cards) {
        super(cards);
    }

    @Override
    public final Hand draw(final Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Hand stay() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean isDrawable() {
        return false;
    }
}
