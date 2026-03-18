package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import blackjack.model.gameresult.GameResult;

public interface HandState {

    GameResult judge(Hand hand, Hand otherHand);

    HandState draw(Hand hand, Card card);

    HandState stay(Hand hand);

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();
}
