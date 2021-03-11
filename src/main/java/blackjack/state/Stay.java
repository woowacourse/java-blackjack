package blackjack.state;

import blackjack.domain.Dealer;

public class Stay extends Finished {

    protected Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(Dealer dealer) {
        if(dealer.isBlackJack() || dealer.getScore() > this.cards().score().getScore()) {
            return -1;
        }
        return 1;
    }


}
