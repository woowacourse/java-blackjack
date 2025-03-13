package blackjack.domain.game;

public enum WinningType {

    WIN("승", ProfitRate.WIN),
    LOSE("패", ProfitRate.LOSE),
    DRAW("무", ProfitRate.DRAW);

    private final String description;
    private final ProfitRate profitRate;

    WinningType(String description, ProfitRate profitRate) {
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

    public ProfitRate getProfitRate() {
        return profitRate;
    }
}
