package domain;

import static domain.GameResult.DRAW;
import static domain.GameResult.LOSE;
import static domain.GameResult.WIN;

public class Player extends Gamer {
    private final PlayerName playerName;

    private static final int BUST_THRESHOLD = 21;

    public Player(PlayerName playerName) {
        super();
        this.playerName = playerName;
    }

    public boolean isDrawable() {
        return this.isDrawable(BUST_THRESHOLD);
    }

    public GameResult decideGameResult(Dealer dealer) {
        if (this.isBust() || dealer.isBust()) {
            return decideGameResultWithBust(dealer);
        }
        return decideGameResultWithoutBust(dealer);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    private GameResult decideGameResultWithBust(Dealer dealer) {
        if (this.isBust() && dealer.isBust()) {
            return DRAW;
        }
        if (this.isBust()) {
            return LOSE;
        }
        return WIN;
    }

    private GameResult decideGameResultWithoutBust(Dealer dealer) {
        int dealerScore = dealer.calculateScore();
        int playerScore = this.calculateScore();
        if (dealerScore == playerScore) {
            return DRAW;
        }
        if (dealerScore > playerScore) {
            return LOSE;
        }
        return WIN;
    }
}
