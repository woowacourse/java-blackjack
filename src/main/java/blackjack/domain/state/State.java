package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.ResultType;

public interface State {

    State update(Hand hand);

    State stay();

    boolean isFinished();

    double profitRate(ResultType match);
}
