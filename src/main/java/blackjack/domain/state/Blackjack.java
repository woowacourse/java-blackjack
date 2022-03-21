package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;

public class Blackjack extends Finished {

    private static final int DRAW_RATE = 0;
    private static final double WIN_RATE = 1.5;

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(Dealer dealer) {
        if (dealer.isBlackjack()) {
            return DRAW_RATE;
        }
        return WIN_RATE;
    }
}
