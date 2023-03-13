package blackjack.domain.player.state;

import blackjack.domain.player.BettingMoney;
import blackjack.domain.player.Hand;
import blackjack.domain.result.Score;

public class Stay extends Finished {
    Stay(Hand hand) {
        super(hand);
    }

    @Override
    public double calculateProfit(boolean isDealerBlackJack, Score dealerScore, Score playerScore, BettingMoney bettingMoney) {
        if (isDealerBlackJack) {
            return bettingMoney.getValue() * NEGATIVE;
        }
        if (dealerScore.isBust() || dealerScore.isLessThan(playerScore)) {
            return bettingMoney.getValue();
        }
        if (dealerScore.equals(playerScore)) {
            return 0;
        }
        return bettingMoney.getValue() * NEGATIVE;
    }
}
