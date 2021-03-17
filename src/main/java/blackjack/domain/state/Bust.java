package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(Dealer dealer) {
        return -1;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
