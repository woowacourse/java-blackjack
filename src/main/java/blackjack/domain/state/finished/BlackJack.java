package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;
import blackjack.domain.money.BetMoney;

public final class BlackJack extends Finished {

    private static final double EARNING_RATE = 1.5;

    public BlackJack(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public boolean isBlackJack() {
        return true;
    }

    @Override
    public double profit(BetMoney betMoney, int dealerScore, boolean isDealerBlackJack) {
        return betMoney.getMoney() * EARNING_RATE;
    }
}
