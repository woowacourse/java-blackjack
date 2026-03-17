package domain.state;

import domain.card.Card;
import domain.member.Hand;

public interface State {
    boolean isFinished();
    boolean isBust();
    boolean isBlackjack();
    double earningRate(State dealerState);
    State draw(Card card);
    State stay();
    Hand hand();
}
