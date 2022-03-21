package blackjack.domain.state;

import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;

public final class Blackjack extends Finished {

    private static final double BLACKJACK_RATE = 1.5;
    private static final double WINNING_RATE = 1;

    Blackjack(HoldCards holdCards) {
        super(holdCards);
    }

    @Override
    protected double earningRate(Dealer dealer) {
        if (dealer.isBlackjack()) {
            return WINNING_RATE;
        }
        return BLACKJACK_RATE;
    }
}
