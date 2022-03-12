package blackjack.domain;


import java.util.List;

public class Dealer extends Gamer {

    private static final int DRAWABLE_LIMIT_VALUE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    public boolean isDrawable() {
        return getTotalScore() <= DRAWABLE_LIMIT_VALUE;
    }

    @Override
    public GameResult createResult(int playerScore) {

        if (playerScore > PLAYING_STANDARD) {
            return GameResult.WIN;
        }

        if (getTotalScore() > PLAYING_STANDARD) {
            return GameResult.LOSE;
        }

        return GameResult.of(getTotalScore() - playerScore);
    }
}

