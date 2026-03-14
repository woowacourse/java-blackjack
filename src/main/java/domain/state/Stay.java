package domain.state;

import domain.participant.Hand;
import java.math.BigDecimal;

public class Stay extends Finished {
    public Stay(Hand hand) { super(hand); }

    @Override
    public BigDecimal calculateProfitRate(State dealerState) {
        if (dealerState.isBust()) return BigDecimal.ONE;

        int compare = this.getScore().compareTo(dealerState.getScore());
        if (compare > 0) {
            return BigDecimal.ONE;
        }
        if (compare < 0) {
            return new BigDecimal("-1.0");
        }
        return BigDecimal.ZERO;
    }
}
