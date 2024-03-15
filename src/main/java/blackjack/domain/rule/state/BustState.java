package blackjack.domain.rule.state;

import blackjack.domain.card.Hands;

public final class BustState extends FinishState {

    BustState(final Hands hands) {
        super(hands);
    }

    @Override
    public State copy() {
        return new BustState(hands());
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
