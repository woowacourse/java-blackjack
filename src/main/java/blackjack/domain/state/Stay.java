package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.user.Dealer;

public class Stay extends Finished {
    public Stay(final Cards cards) {
        super(cards);
    }

    @Override
    public double profitRate(Dealer dealer) {
        Score score = this.cards.calculateScore();
        if (dealer.isBust() || score.isHigh(dealer)) {
            return 1;
        }
        if (!dealer.isBust() && score.isSame(dealer)) {
            return 0;
        }
        return -1;
    }
}
