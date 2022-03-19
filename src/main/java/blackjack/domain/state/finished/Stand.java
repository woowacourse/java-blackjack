package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;
import blackjack.domain.money.BetMoney;

public final class Stand extends Finished {

    private static final int LOSE_EARNING_RATE = -1;

    public Stand(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public double profit(BetMoney betMoney, int dealerScore) {
        int score = super.cardSum();
        if (score > dealerScore) {
            return betMoney.getMoney();
        }
        if (score < dealerScore) {
            return betMoney.getMoney() * LOSE_EARNING_RATE;
        }
        return 0;
    }
}
