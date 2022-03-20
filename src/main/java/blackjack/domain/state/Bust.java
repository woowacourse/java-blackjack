package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;

public class Bust extends Finished {

    Bust(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(Dealer dealer) {
        return -1;
    }
}
