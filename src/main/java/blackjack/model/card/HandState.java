package blackjack.model.card;

import blackjack.model.gameresult.GameResult;

public interface HandState {

    GameResult judge(Hand hand, Hand otherHand);

    void draw(Hand hand, Card card);

    boolean isBlackjack();

    boolean isBust();
}
