package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;
import blackjack.domain.money.BetMoney;

public final class Stand extends Finished {

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
            return betMoney.getMoney() * -1;
        }
        return 0;
    }
}
