package blackjack.model.card;

import blackjack.model.gameresult.GameResult;

public class Stay extends Finished{

    @Override
    public GameResult judge(Hand hand, Hand otherHand) {
        if (otherHand.isBlackjack()) {
            return GameResult.LOSE;
        }

        if (otherHand.isBust()) {
            return GameResult.WIN;
        }

        return judgeWithScore(hand, otherHand);
    }

    @Override
    public void draw(Hand hand, Card card) {
        throw new IllegalStateException("이미 턴이 종료되어 카드를 추가로 받을 수 없습니다.");
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
        return false;
    }

    private GameResult judgeWithScore(Hand hand, Hand otherHand) {
        if (hand.calculateTotalScore() == otherHand.calculateTotalScore()) {
            return GameResult.DRAW;
        }

        if (hand.calculateTotalScore() > otherHand.calculateTotalScore()) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }
}
