package blackjack.domain.state;

import blackjack.domain.Hand;

public interface State {

    State draw(Hand hand);

    State stay();

    boolean isFinished();
}
