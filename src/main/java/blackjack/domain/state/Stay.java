package blackjack.domain.state;

import blackjack.domain.user.Cards;
import blackjack.domain.user.Dealer;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(Dealer dealer) {
        if (dealer.isBust() || this.cards.totalScore().isGreater(dealer.score())) {
            return 1;
        }
        if (!dealer.isBust() && this.cards.totalScore().isSame(dealer.score())) {
            return 0;
        }
        return -1;
    }
}
