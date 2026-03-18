package domain.state;

import domain.Result;
import domain.card.Card;
import domain.card.Hand;

public interface State {

    State draw(Card card);

    State stay();

    boolean isFinished();

    Result judge(State state);

    Hand hand();
}
