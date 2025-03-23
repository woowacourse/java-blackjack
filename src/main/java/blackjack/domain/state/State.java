package blackjack.domain.state;

import blackjack.domain.card.Hand;
import java.math.BigDecimal;

public interface State {

    State receiveCards(Hand hand);

    State stay();

    boolean isNotFinished();

    BigDecimal profit(BigDecimal bettingAmount, State dealerState);

    int calculateScore();

    Hand cards();

    StateType getStateType();
}
