package domain;

import static domain.GameResult.DRAW;
import static domain.GameResult.LOSE;
import static domain.GameResult.WIN;

public class Dealer extends Gamer {

    private static final int BUST_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public GameResult decideGameResult(Player player) {
        int dealerScore = this.calculateScore();
        int playerScore = player.calculateScore();

        if (dealerScore == playerScore) {
            return DRAW;
        }

        if (dealerScore > playerScore || playerScore > 21) {
            return WIN;
        }
        return LOSE;
    }

    public boolean canGetMoreCard() {
        return this.canGetMoreCard(BUST_THRESHOLD);
    }
}
