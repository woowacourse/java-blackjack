package blackjack.domain.state;

import blackjack.domain.participant.Hand;

public interface State {
    boolean isFinished();

    State check(final Hand hand);

    State stay();

    double profit(final double money);
}
