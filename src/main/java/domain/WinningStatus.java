package domain;

public enum WinningStatus {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String symbol;

    WinningStatus(String symbol) {
        this.symbol = symbol;
    }

    public static WinningStatus of(Player player, Dealer dealer) {
        int playerScore = player.score();
        int dealerScore = dealer.score();

        if (playerScore > 21) {
            return LOSE;
        }

        if (dealerScore > 21) {
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
