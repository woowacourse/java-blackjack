package blackjack.state;

import blackjack.domain.User;

public class Stay extends Finished {

    protected Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(User dealer) {
        if (dealer.isBlackJack() ||
                (!dealer.isBust() && dealer.getScore() > this.cards().score().getScore())) {
            return -1;
        }
        return 1;
    }


}
