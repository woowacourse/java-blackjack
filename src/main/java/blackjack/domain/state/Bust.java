package blackjack.domain.state;

import blackjack.domain.participant.Dealer;

public class Bust extends Finished {

    @Override
    void setState(Dealer dealer, int score) {
        super.resultType = ResultType.LOSE;
    }
}
