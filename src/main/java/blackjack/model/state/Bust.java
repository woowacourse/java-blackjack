package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import blackjack.model.gameresult.GameResult;

public class Bust extends Finished{

    @Override
    public GameResult judge(Hand hand, Hand otherHand) {
        return GameResult.LOSE;
    }

    @Override
    public HandState draw(Hand hand, Card card) {
        return this;
//        throw new IllegalStateException("버스트 상태에서는 카드를 추가로 받을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
