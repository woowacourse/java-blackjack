package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public abstract class Finish extends Started {
    protected Finish(Cards cards) {
        super(cards);
    }

    @Override
    public final State draw(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean isFinish() {
        return true;
    }

    @Override
    public final State stay() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal profit(State dealerState, BigDecimal bigDecimal) {
        return getEarningRates(dealerState).multiply(bigDecimal);
    }

    public abstract BigDecimal getEarningRates(State dealerState);
}
