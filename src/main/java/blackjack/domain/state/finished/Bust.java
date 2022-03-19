package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;
import blackjack.domain.money.BetMoney;

public final class Bust extends Finished {

    private static final int EARNING_RATE = -1;

    public Bust(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public double profit(BetMoney betMoney, int dealerScore, boolean isDealerBlackJack) {
        return betMoney.getMoney() * EARNING_RATE;
    }
}
