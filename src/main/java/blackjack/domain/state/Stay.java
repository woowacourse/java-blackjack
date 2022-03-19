package blackjack.domain.state;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.PlayerCards;

public class Stay extends Finished {

    Stay(PlayerCards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public Profit profit(Outcome outcome, BetMoney money) {
        if (outcome.equals(Outcome.WIN)) {
            return money.getProfit(WIN_RATE);
        }
        if (outcome.equals(Outcome.LOSE)) {
            return money.getProfit(LOSE_RATE);
        }
        return money.getProfit(DRAW_RATE);
    }
}
