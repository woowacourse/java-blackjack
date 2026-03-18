package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import blackjack.model.gameresult.GameResult;

public class Hit implements HandState {

    private static final int BLACKJACK_RANK = 21;
    private static final int BLACKJACK_CHECK_SIZE = 2;

    @Override
    public GameResult judge(Hand hand, Hand otherHand) {
        throw new IllegalStateException("아직 턴이 종료되지 않았습니다.");
    }

    @Override
    public HandState draw(Hand hand, Card card) {
        hand.addCard(card);

        if (hand.calculateTotalScore() > BLACKJACK_RANK) {
            return new Bust();
        }

        if (hand.calculateTotalScore() == BLACKJACK_RANK) {
            return checkBlackjack(hand);
        }

        return this;
    }

    @Override
    public HandState stay(Hand hand) {
        return new Stay();
    }

    @Override
    public boolean isFinished() {
        return false;
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

    private HandState checkBlackjack(Hand hand) {
        if (hand.size() == BLACKJACK_CHECK_SIZE) {
            return new Blackjack();
        }
        return new Stay();
    }
}
