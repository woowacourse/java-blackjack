package blackjack.game;

import blackjack.user.Dealer;
import blackjack.user.Player;

public enum GameResult {
    WIN(),
    DRAW(),
    LOSE();

    GameResult() {
    }

    public static GameResult FromDenominationsSum(final Dealer dealer, final Player player) {
        int dealerSum = dealer.getCards().calculateDenominations();
        int playerSum = player.getCards().calculateDenominations();

        if (dealerSum < playerSum) {
            return WIN;
        }
        if (dealerSum == playerSum) {
            return DRAW;
        }
        return LOSE;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isDraw() {
        return this == DRAW;
    }

    public boolean isLose() {
        return this == LOSE;
    }
}
