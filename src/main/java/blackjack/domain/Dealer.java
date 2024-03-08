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

    public GameResult judge(Player player) {
        int playerScore = player.calculateScore();
        int dealerScore = calculateScore();

        if (player.isBust()) {
            return GameResult.LOSE;
        }

        if (isBust() || playerScore > dealerScore) {
            return GameResult.WIN;
        }

        if (player.isBlackJack()) {
            return GameResult.TIE;
        }

        return GameResult.LOSE;
    }
}
