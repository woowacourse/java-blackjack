package blackjack.domain.state.finished;

import blackjack.domain.participant.Dealer;

public class Stay extends Finished {

    @Override
    protected ResultType getResultType(Dealer dealer, int score) {
        if (dealer.isBust()) {
            return ResultType.WIN;
        }
        return getResultTypeByDifference(score - dealer.getScore());
    }
}
