package domain.state;

import domain.participant.Hand;
import java.math.BigDecimal;

public class Blackjack extends Finished {
    public Blackjack(Hand hand) { super(hand); }

    @Override
    public BigDecimal calculateProfitRate(State dealerState) {
        if (dealerState.isBlackjack()) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal("1.5");
    }
}
