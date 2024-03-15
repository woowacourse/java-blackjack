package blackjack.domain.participant.state;

import blackjack.domain.card.CardHand;
import blackjack.domain.participant.ResultStatus;
import blackjack.utils.Constants;

public final class Blackjack extends Finished {

    Blackjack(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public double calculateEarningRate(final ResultStatus resultStatus) {
        if (resultStatus.isDraw()) {
            return Constants.DRAW_EARNING_RATE;
        }
        return Constants.BLACKJACK_EARNING_RATE;
    }
}
