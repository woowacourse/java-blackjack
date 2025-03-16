package domain.state;

import domain.card.Card;
import domain.card.Hand;

public interface State {
    State hit(Card card);

    State stay();

    boolean isFinished();

    double getProfit(int bettingAmount);

    Hand getHand();
}
