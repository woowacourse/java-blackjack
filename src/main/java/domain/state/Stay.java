package domain.state;

import domain.participant.Hand;
import java.math.BigDecimal;

public class Stay extends Finished {

    public Stay(Hand hand) { super(hand); }

    @Override
    public BigDecimal calculateProfitRate(State dealerState) {
        if (dealerState.isBust()) return PROFIT_RATE_WIN;

        int compare = this.getScore().compareTo(dealerState.getScore());
        if (compare > 0) {
            return PROFIT_RATE_WIN;
        }
        if (compare < 0) {
            return PROFIT_RATE_LOSE;
        }
        return PROFIT_RATE_TIE;
    }
}
