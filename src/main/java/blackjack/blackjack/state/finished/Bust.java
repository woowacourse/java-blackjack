package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import java.math.BigDecimal;

public final class Bust extends Finished {

    public Bust(final Hand cards) {
        super(cards, StateType.BUST);
    }

    @Override
    public BigDecimal calculateProfit(final BigDecimal bettingAmount, final State dealerState) {
        return bettingAmount.multiply(BigDecimal.valueOf(-1));
    }
}
