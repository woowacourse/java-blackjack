package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.participant.Dealer;
import blackjack.blackjack.state.StateType;
import java.math.BigDecimal;

public final class Bust extends Finished {

    public Bust(final Hand hand) {
        super(hand, StateType.BUST);
    }

    @Override
    public BigDecimal calculateProfit(final BigDecimal bettingAmount, final Dealer dealer) {
        return bettingAmount.multiply(BigDecimal.valueOf(-1));
    }
}
