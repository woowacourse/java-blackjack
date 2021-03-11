package blackjack.state;

import blackjack.domain.Dealer;

public class Bust extends Finished {

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(Dealer dealer) {
        if (dealer.isBust()) {
            return 0;
        }
        return -1;
    }
}
