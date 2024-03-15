package domain.state;

import domain.card.Card;

public interface State {
    State add(Card card);

    State stand();

    double profit();

    boolean isFinished();

    boolean isBust();

    boolean canHit(int hitUpperBound);

    Hands getHands();

    int getScore();
}
