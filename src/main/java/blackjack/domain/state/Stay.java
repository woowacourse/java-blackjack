package blackjack.domain.state;

import blackjack.domain.participant.Dealer;

public class Stay extends Finished {

    @Override
    protected void setState(Dealer dealer, int score) {
        if (dealer.isBust()) {
            super.resultType = ResultType.WIN;
            return;
        }
        super.resultType = ResultType.getResultType(score - dealer.getScore());
    }
}
