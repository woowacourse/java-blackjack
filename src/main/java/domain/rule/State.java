package domain.rule;

import domain.card.Card;
import domain.card.Cards;

public interface State {
    State draw(Card card);
    State stay();
    boolean isBust();
    boolean isBlackjack();
    boolean isFinished();
    Cards cards();
    double profit(double betAmount, State dealerState);
}
