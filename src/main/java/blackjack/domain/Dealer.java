package blackjack.domain;

public class Dealer extends Participant {

    private static final int STAND_BOUND = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isPlayable() {
        int score = calculateScore();

        return score < STAND_BOUND;
    }

    public GameResult judge(Player player) {
        int playerScore = player.calculateScore();
        int dealerScore = calculateScore();

        if (player.isBust()) {
            return GameResult.LOSE;
        }

        if (player.isBlackJack()) {
            return judgeWhenPlayerIsBlackjack();
        }

        return judgeWhenPlayerNormalScore(playerScore, dealerScore);
    }

    private GameResult judgeWhenPlayerIsBlackjack() {
        if (isBlackJack()) {
            return GameResult.PUSH;
        }

        return GameResult.WIN;
    }

    private GameResult judgeWhenPlayerNormalScore(int playerScore, int dealerScore) {
        if (isBust() || playerScore > dealerScore) {
            return GameResult.WIN;
        }

        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }

        return GameResult.PUSH;
    }
}
