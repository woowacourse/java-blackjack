package blackjack.domain;

public class Dealer extends Participant {

    private static final int STAND_BOUND = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isPlayable() {
        int score = hand.calculateScore();

        return score < STAND_BOUND;
    }

    public GameResult compareWith(Player player) {
        int playerScore = player.calculateScore();
        int dealerScore = calculateScore();

        if (player.isBust()) {
            return GameResult.WIN;
        }

        if (isBust() || playerScore > dealerScore) {
            return GameResult.LOSE;
        }

        if (player.isBlackJack()) {
            return GameResult.DRAW;
        }

        return GameResult.WIN;
    }
}
