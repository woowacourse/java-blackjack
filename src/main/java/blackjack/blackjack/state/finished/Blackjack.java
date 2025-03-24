package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.participant.Dealer;
import blackjack.blackjack.state.StateType;
import java.math.BigDecimal;

public final class Blackjack extends Finished {

    private static final BigDecimal BLACKJACK_PROFIT_RATE = BigDecimal.valueOf(1.5);

    public Blackjack(final Hand cards) {
        super(cards, StateType.BLACKJACK);
    }

    @Override
    public BigDecimal calculateProfit(final BigDecimal bettingAmount, final Dealer dealer) {
        return bettingAmount.multiply(BLACKJACK_PROFIT_RATE);
    }
}
