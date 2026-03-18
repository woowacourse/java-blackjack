package blackjack.model.card;

import blackjack.model.gameresult.GameResult;

public class Bust implements HandState{

    @Override
    public GameResult judge(Hand hand, Hand otherHand) {
        return GameResult.LOSE;
    }

    @Override
    public void draw(Hand hand, Card card) {
        throw new IllegalArgumentException("버스트 상태에서는 카드를 추가로 받을 수 없습니다.");
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
