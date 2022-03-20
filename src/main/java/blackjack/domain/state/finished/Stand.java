package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;
import blackjack.domain.money.BetMoney;

import static blackjack.domain.card.HoldingCards.BUST_STANDARD;

public final class Stand extends Finished {

    private static final int LOSE_EARNING_RATE = -1;

    public Stand(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public double profit(BetMoney betMoney, int dealerScore, boolean isDealerBlackJack) {
        if (isDealerBlackJack) {
            return betMoney.getMoney();
        }
        return profitNotDealerBlackJack(betMoney, dealerScore);
    }

    private int profitNotDealerBlackJack(BetMoney betMoney, int dealerScore) {
        int score = super.cardScore();
        if (dealerScore > BUST_STANDARD || score > dealerScore) {
            return betMoney.getMoney();
        }
        if (score < dealerScore) {
            return betMoney.getMoney() * LOSE_EARNING_RATE;
        }
        return 0;
    }
}
