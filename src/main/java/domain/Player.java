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
        int playerScore = this.calculateScore();
        int dealerScore = dealer.calculateScore();
        if (playerScore > BUST_THRESHOLD || dealerScore > BUST_THRESHOLD) {
            return decideGameResultWithBust(playerScore, dealerScore);
        }
        return decideGameResultWithoutBust(playerScore, dealerScore);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    private GameResult decideGameResultWithBust(int playerScore, int dealerScore) {
        if (playerScore > BUST_THRESHOLD && dealerScore > BUST_THRESHOLD) {
            return DRAW;
        }
        if (playerScore > BUST_THRESHOLD) {
            return LOSE;
        }
        return WIN;
    }

    private GameResult decideGameResultWithoutBust(int playerScore, int dealerScore) {
        if (dealerScore == playerScore) {
            return DRAW;
        }
        if (dealerScore > playerScore) {
            return LOSE;
        }
        return WIN;
    }
}
