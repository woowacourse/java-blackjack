package domain;

import static domain.GameResult.DRAW;
import static domain.GameResult.LOSE;
import static domain.GameResult.WIN;

public class Player extends Gamer {
    private final PlayerName playerName;
    
    public Player(PlayerName playerName) {
        super();
        this.playerName = playerName;
    }

    public boolean isDrawable() {
        return this.isDrawable(GAMER_BUST_THRESHOLD);
    }

    public GameResult decideGameResult(Dealer dealer) {
        int playerScore = this.getScore();
        int dealerScore = dealer.getScore();
        if (playerScore > GAMER_BUST_THRESHOLD || dealerScore > GAMER_BUST_THRESHOLD) {
            return decideGameResultWithBust(playerScore, dealerScore);
        }
        return decideGameResultWithoutBust(playerScore, dealerScore);
    }

    private GameResult decideGameResultWithBust(int playerScore, int dealerScore) {
        if (playerScore > GAMER_BUST_THRESHOLD && dealerScore > GAMER_BUST_THRESHOLD) {
            return DRAW;
        }
        if (playerScore > GAMER_BUST_THRESHOLD) {
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
