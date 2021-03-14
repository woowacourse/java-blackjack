package blackjack.state;

import blackjack.domain.Dealer;
import blackjack.domain.User;

public class Bust extends Finished {

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(User dealer) {
        return -1;
    }
}
