package blackjack.state;

import blackjack.domain.Dealer;

public class Bust extends Finished {

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(Dealer dealer) {
        return -1;
    }
}
