package blackjack.domain.state.running;

import blackjack.domain.card.HoldingCards;
import blackjack.domain.money.BetMoney;
import blackjack.domain.state.Started;

public abstract class Running extends Started {

    public Running(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public final double profit(BetMoney betMoney, int dealerScore, boolean isDealerBlackJack) {
        throw new IllegalStateException();
    }

    @Override
    public final boolean isBlackJack() {
        return false;
    }
}
