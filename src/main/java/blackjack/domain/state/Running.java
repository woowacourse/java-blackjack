package blackjack.domain.state;

import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public abstract class Running extends Started {
    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public final boolean isFinish() {
        return false;
    }

    @Override
    public final State stay() {
        return new Stay(cards());
    }

    @Override
    public BigDecimal profit(State dealerState, BigDecimal bigDecimal) {
        throw new UnsupportedOperationException();
    }
}
