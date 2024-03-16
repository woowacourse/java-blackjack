package domain.state;

import domain.card.Card;
import domain.card.Hands;

public interface State {
    State add(Card card);

    State stand();

    double earningRate();

    boolean canHit(int hitUpperBound);

    boolean isFinished();
    boolean isRunning();

    boolean isBust();

    boolean isBlackjack();

    boolean isStand();

    Hands getHands();

    int getScore();
}
