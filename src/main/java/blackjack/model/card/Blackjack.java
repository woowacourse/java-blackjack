package blackjack.model.card;

import blackjack.model.gameresult.GameResult;

public class Blackjack implements HandState {

    @Override
    public GameResult judge(Hand hand, Hand otherHand) {
        if (otherHand.isBlackjack()) {
            return GameResult.DRAW;
        }
        return GameResult.BLACKJACK_WIN;
    }

    @Override
    public void draw(Hand hand, Card card) {
        throw new IllegalArgumentException("블랙잭 상태에서는 카드를 추가로 받을 수 없습니다.");
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
