package blackjack.domain.state;

import blackjack.domain.Hand;

public interface State {

    State update(Hand hand);

    State stay();

    boolean isFinished();
}
