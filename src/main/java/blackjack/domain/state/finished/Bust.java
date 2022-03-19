package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;
import blackjack.domain.money.BetMoney;

public final class Bust extends Finished {

    public Bust(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public double profit(BetMoney betMoney, int dealerScore) {
        return betMoney.getMoney() * -1;
    }
}
