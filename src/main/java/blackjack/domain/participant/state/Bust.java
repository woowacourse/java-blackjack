package blackjack.domain.participant.state;

import blackjack.domain.card.CardHand;
import blackjack.domain.participant.ResultStatus;
import blackjack.domain.participant.state.constants.RateConstants;

public final class Bust extends Finished {

    Bust(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public double calculateEarningRate(final ResultStatus resultStatus) {
        return RateConstants.LOSE_EARNING_RATE;
    }
}
