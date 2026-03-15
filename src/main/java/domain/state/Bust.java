package domain.state;

import domain.participant.Hand;
import java.math.BigDecimal;

public class Bust extends Finished {
    public Bust(Hand hand) { super(hand); }

    @Override
    public BigDecimal calculateProfitRate(State dealerState) {
        return PROFIT_RATE_LOSE;
    }
}
