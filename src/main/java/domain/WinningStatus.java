package domain;

import domain.participant.Dealer;
import domain.participant.Player;

public enum WinningStatus {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    public static final int BLACK_JACK = 21;

    private final String symbol;

    WinningStatus(String symbol) {
        this.symbol = symbol;
    }

    public static WinningStatus of(Player player, Dealer dealer) {
        int playerScore = player.score();
        int dealerScore = dealer.score();

        if (playerScore > BLACK_JACK) {
            return LOSE;
        }

        if (dealerScore > BLACK_JACK) {
            return WIN;
        }
        return compareScore(playerScore, dealerScore);
    }

    private static WinningStatus compareScore(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return TIE;
    }

    public String getSymbol() {
        return symbol;
    }
}
