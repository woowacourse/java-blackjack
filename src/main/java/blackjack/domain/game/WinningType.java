package blackjack.domain.game;

public enum WinningType {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    WinningType(String description) {
        this.description = description;
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
        playerPoint = GameRule.processBustPoint(playerPoint);
        dealerPoint = GameRule.processBustPoint(dealerPoint);

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
}
