package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;

public class Bust extends Finished {

    private static final int LOSE_RATE = -1;

    Bust(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(Dealer dealer) {
        return LOSE_RATE;
    }
}
