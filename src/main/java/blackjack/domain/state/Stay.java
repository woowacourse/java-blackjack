package blackjack.domain.state;

import blackjack.domain.user.Cards;
import blackjack.domain.user.Dealer;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(Dealer dealer) {
        return 1;
    }
}
