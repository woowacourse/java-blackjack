package blackjack.model.card;

import blackjack.model.gameresult.GameResult;

public class Hit implements HandState{

    private static final int BLACKJACK_RANK = 21;
    private static final int BLACKJACK_CHECK_SIZE = 2;

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
        hand.addCard(card);

        if (hand.calculateTotalScore() > BLACKJACK_RANK) {
            hand.setState(new Bust());
            return;
        }

        if (hand.size() == BLACKJACK_CHECK_SIZE) {
            checkBlackjack(hand);
        }
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

    private static void checkBlackjack(Hand hand) {
        if (hand.calculateTotalScore() == BLACKJACK_RANK) {
            hand.setState(new Blackjack());
        }
    }
}
