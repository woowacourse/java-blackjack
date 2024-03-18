package blackjack.domain.participant.state;

import blackjack.domain.card.CardHand;
import blackjack.domain.participant.ResultStatus;
import blackjack.domain.participant.state.constants.RateConstants;

public final class Stay extends Finished {

    Stay(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public double calculateEarningRate(final ResultStatus resultStatus) {
        if (resultStatus.isWin()) {
            return RateConstants.WIN_EARNING_RATE;
        }
        if (resultStatus.isDraw()) {
            return RateConstants.DRAW_EARNING_RATE;
        }
        return RateConstants.LOSE_EARNING_RATE;
    }
}
