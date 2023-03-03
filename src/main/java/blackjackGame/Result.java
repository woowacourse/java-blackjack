package blackjackGame;

import player.Dealer;
import player.Player;

public enum Result {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String label;

    Result(String label) {
        this.label = label;
    }

    public static Result calculateWinning(Player player, Dealer dealer) {
        boolean playerBust = player.isBust();
        int playerScore = player.calculateScore();
        boolean dealerBust = dealer.isBust();
        int dealerScore = dealer.calculateScore();
        if ((playerScore > dealerScore && !playerBust) || (dealerBust && !playerBust)) {
            return WIN;
        }
        if (playerBust || playerScore < dealerScore) {
            return LOSE;
        }
        return TIE;
    }

    public String getLabel() {
        return label;
    }
}
