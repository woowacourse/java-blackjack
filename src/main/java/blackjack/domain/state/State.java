package blackjack.domain.state;

import blackjack.domain.Response;
import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;

public interface State {

    boolean isFinished();

    double getEarningRate();

    State draw(Card card);

    Hand getHand();

    State moveStateByResponse(Response response);

    default int getScore() {
        return getHand().getScore();
    }
}
