package blackjack.domain.player.state;

import blackjack.domain.player.BettingMoney;
import blackjack.domain.player.Hand;
import blackjack.domain.result.Score;

public class Blackjack extends Finished {
    private static final double BLACKJACK_EARNING_RATE = 1.5;

    Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public double calculateProfit(boolean isDealerBlackJack, Score dealerScore, BettingMoney bettingMoney) {
        if (isDealerBlackJack) {
            return 0;
        }
        return BLACKJACK_EARNING_RATE * bettingMoney.getValue();
    }
}
