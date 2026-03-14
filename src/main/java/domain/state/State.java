package domain.state;

import domain.card.Card;
import domain.member.Hand;

public interface State {
    boolean isFinished();
    double earningRate();
    State draw(Card card);
    State stay();
    Hand hand();
}
