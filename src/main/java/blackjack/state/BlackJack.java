package blackjack.state;

import blackjack.domain.Dealer;

public class BlackJack extends Finished {

    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(Dealer dealer) {
        if(dealer.isBlackJack()){
            return 1;
        }
        return 1.5;
    }
}
