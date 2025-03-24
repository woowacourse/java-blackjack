package blackjack.blackjack.state;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.participant.Dealer;
import java.math.BigDecimal;

public interface State {

    State receiveCards(Hand hand);

    State stay();

    boolean isNotFinished();

    BigDecimal calculateProfit(BigDecimal bettingAmount, Dealer dealer);

    int calculateScore();

    Hand cards();

    StateType getStateType();
}
