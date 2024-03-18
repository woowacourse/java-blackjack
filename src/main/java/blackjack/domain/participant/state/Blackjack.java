package blackjack.domain.participant.state;

import blackjack.domain.card.CardHand;
import blackjack.domain.participant.ResultStatus;
import blackjack.domain.participant.state.constants.RateConstants;

public final class Blackjack extends Finished {

    Blackjack(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public double calculateEarningRate(final ResultStatus resultStatus) {
        if (resultStatus.isDraw()) {
            return RateConstants.DRAW_EARNING_RATE;
        }
        return RateConstants.BLACKJACK_EARNING_RATE;
    }
}
