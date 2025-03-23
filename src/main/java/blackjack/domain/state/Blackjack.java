package blackjack.domain.state;

import blackjack.domain.card.Hand;
import java.math.BigDecimal;

public final class Blackjack extends Finished {

    private static final BigDecimal BLACKJACK_PROFIT_RATE = BigDecimal.valueOf(1.5);

    public Blackjack(final Hand cards) {
        super(cards, StateType.BLACKJACK);
    }

    @Override
    public State receiveCards(final Hand hand) {
        cards.addAll(hand);
        return this;
    }

    @Override
    public BigDecimal profit(final BigDecimal bettingAmount, final State dealerState) {
        return bettingAmount.multiply(BLACKJACK_PROFIT_RATE);
    }
}
