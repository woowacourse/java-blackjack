package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String text;

    GameResult(final String text) {
        this.text = text;
    }

    public static GameResult FromDenominationsSum(final Dealer dealer, Player player) {
        int dealerSum = dealer.calculateDenominations();
        int playerSum = player.calculateDenominations();

        if (dealerSum < playerSum) {
            return WIN;
        }
        if (dealerSum == playerSum) {
            return DRAW;
        }
        return LOSE;
    }

    public GameResult changeStatusOpposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getText() {
        return text;
    }
}
