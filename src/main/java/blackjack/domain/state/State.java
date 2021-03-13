package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.participant.Dealer;

public interface State {

    State update(Hand hand);

    State stay();

    boolean isFinished();

    double profitRate(Dealer dealer, int score);
}
