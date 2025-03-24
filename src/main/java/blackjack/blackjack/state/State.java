package blackjack.blackjack.state;

import blackjack.blackjack.card.Hand;

public interface State {

    State receiveCards(Hand hand);

    State stay();

    boolean isNotFinished();

    int calculateScore();

    Hand cards();

    StateType getStateType();
}
