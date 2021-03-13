package blackjack.domain.state;

import blackjack.domain.participant.Dealer;

public class BlackJackState extends Finished {

    @Override
    void setState(Dealer dealer, int score) {
        if (dealer.getHand().isBlackJack()) {
            super.resultType = ResultType.TIE;
            return;
        }
        super.resultType = ResultType.BLACKJACK;
    }
}
