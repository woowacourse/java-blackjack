package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;

public class Bust extends Finished {
    public Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public double profitRate(Dealer dealer) {
        return -1;
    }
}
