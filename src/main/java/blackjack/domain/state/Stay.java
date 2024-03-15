package blackjack.domain.state;

import blackjack.domain.card.CardHand;
import blackjack.domain.game.ResultStatus;
import blackjack.utils.Constants;

public final class Stay extends Finished {

    Stay(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public double calculateEarningRate(final ResultStatus resultStatus) {
        if (resultStatus.isWin()) {
            return Constants.WIN_EARNING_RATE;
        }
        if (resultStatus.isDraw()) {
            return Constants.DRAW_EARNING_RATE;
        }
        return Constants.LOSE_EARNING_RATE;
    }
}
