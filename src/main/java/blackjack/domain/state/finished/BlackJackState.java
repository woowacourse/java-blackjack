package blackjack.domain.state.finished;

import blackjack.domain.participant.Dealer;

public class BlackJackState extends Finished {

    @Override
    ResultType getState(Dealer dealer, int score) {
        if (dealer.getHand().isBlackJack()) {
            return ResultType.TIE;
        }
        return ResultType.BLACKJACK;
    }
}
