package domain.rule;

import domain.card.Card;
import domain.card.Hand;

public interface State {
    State draw(Card card);
    State stay();
    boolean isBust();
    boolean isBlackjack();
    boolean isFinished();
    Hand cards();
    double profit(double betAmount, State dealerState);
}
