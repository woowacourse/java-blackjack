package blackjack.domain.game;

public enum WinningType {

    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0);

    private final String description;
    private final int profitRate;

    WinningType(String description, int profitRate) {
        this.description = description;
        this.profitRate = profitRate;
    }

    public WinningType reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public static WinningType parse(int playerPoint, int dealerPoint) {
        playerPoint = GameRule.applyBustToPoint(playerPoint);
        dealerPoint = GameRule.applyBustToPoint(dealerPoint);

        if (playerPoint > dealerPoint) {
            return WinningType.WIN;
        }
        if (playerPoint < dealerPoint) {
            return WinningType.LOSE;
        }
        return DRAW;
    }

    public String getDescription() {
        return description;
    }

    public int getProfitRate() {
        return profitRate;
    }
}
