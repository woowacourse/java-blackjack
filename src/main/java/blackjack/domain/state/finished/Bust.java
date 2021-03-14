package blackjack.domain.state.finished;

import blackjack.domain.participant.Dealer;

public class Bust extends Finished {

    @Override
    ResultType getResultType(Dealer dealer, int score) {
        return ResultType.LOSE;
    }
}
