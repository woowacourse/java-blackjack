package blackjack.domain.player.state;

import blackjack.domain.player.BettingMoney;
import blackjack.domain.player.Hand;
import blackjack.domain.result.Score;

public class Stay extends Finished {
    Stay(Hand hand) {
        super(hand);
    }

    @Override
    public double calculateProfit(boolean isDealerBlackJack, Score dealerScore, BettingMoney bettingMoney) {
        return 0;
    }
}
