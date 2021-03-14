package blackjack.state;

import blackjack.domain.Dealer;
import blackjack.domain.User;

public class BlackJack extends Finished {

    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(User dealer) {
        if (dealer.isBlackJack()) {
            return 1;
        }
        return 1.5;
    }
}
