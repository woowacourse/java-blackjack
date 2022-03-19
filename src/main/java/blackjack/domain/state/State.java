package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface State {

    State draw(Card card);

    State stay();

    boolean isRunning();

    boolean isFinished();

    Cards cards();

    double earningRate(State state);
}
