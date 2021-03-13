package blackjack.domain.state.finished;

import blackjack.domain.participant.Dealer;
import blackjack.domain.state.finished.Finished;

public class Stay extends Finished {

    @Override
    protected ResultType getState(Dealer dealer, int score) {
        if (dealer.isBust()) {
            return ResultType.WIN;
        }
        return getResultType(score - dealer.getScore());
    }
}
