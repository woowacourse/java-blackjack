package blackjack.model.card;

import blackjack.model.gameresult.GameResult;

public interface HandState {

    GameResult judge(Hand hand, Hand otherHand);

    void draw(Hand hand, Card card);

    void stay(Hand hand);

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();
}
