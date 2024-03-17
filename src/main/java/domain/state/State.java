package domain.state;

import domain.card.Card;
import domain.card.Hands;
import domain.player.Result;

public interface State {
    State add(Card card);

    State stand();

    double earningRate();

    boolean isFinished();

    Hands getHands();

    int getScore();

    Result compareWith(State state);
}
