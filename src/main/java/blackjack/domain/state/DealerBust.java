package blackjack.domain.state;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.PlayerCards;

public class DealerBust extends Bust {

    DealerBust(PlayerCards cards) {
        super(cards);
    }

    @Override
    public Profit profit(Outcome outcome, BetMoney money) {
        throw new IllegalStateException();
    }
}
