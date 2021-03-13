package blackjack.domain.state.finished;

import blackjack.domain.participant.Dealer;

public class Bust extends Finished {

    @Override
    ResultType getState(Dealer dealer, int score) {
        return ResultType.LOSE;
    }
}
