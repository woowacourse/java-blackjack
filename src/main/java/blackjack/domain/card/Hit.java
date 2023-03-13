package blackjack.domain.card;

public final class Hit extends Running {

    public Hit() {
        this(new Cards());
    }

    public Hit(final Cards cards) {
        super(cards);
    }

    @Override
    public Hand draw(final Card card) {
        cards().add(card);
        return calculateState();
    }

    private Hand calculateState() {
        if (cards().isBlackjack()) {
            return new Blackjack(cards());
        }
        if (cards().isBust()) {
            return new Bust(cards());
        }
        if (cards().isBlackjackScore()) {
            return stay();
        }
        return this;
    }

    @Override
    public Hand stay() {
        return new Stay(cards());
    }
}
