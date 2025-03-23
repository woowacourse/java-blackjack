package blackjack.blackjack.state;

import blackjack.blackjack.card.Hand;
import java.math.BigDecimal;

public interface State {

    State receiveCards(Hand hand);

    State stay();

    boolean isNotFinished();

    BigDecimal calculateProfit(BigDecimal bettingAmount, State dealerState);

    int calculateScore();

    Hand cards();

    StateType getStateType();
}
