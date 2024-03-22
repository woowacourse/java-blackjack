package domain.state;

import domain.card.Card;
import domain.card.Hands;
import domain.player.Result;

public interface State {
    State add(Card card);

    State stand();

    Result compareWith(State state);

    double earningRate();

    boolean isFinished();

    boolean isRunning();

    Hands getHands();

    int getScore();
}
