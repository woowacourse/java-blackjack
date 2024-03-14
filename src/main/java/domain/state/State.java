package domain.state;

import domain.card.Card;
import domain.player.Hands;

public interface State {
    State draw(Card card);

    State stand();

    boolean isFinished();

    double profit();

    Hands getHands();
}
