package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public interface State {

    State draw(Card card);

    State stay();

    boolean isFinished();

    Hand hand();

    double earningRate(State state);

    boolean isRunning();
}
