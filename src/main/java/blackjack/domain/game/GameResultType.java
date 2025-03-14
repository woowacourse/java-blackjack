package blackjack.domain.game;

public enum GameResultType {

    WIN_WITH_INITIAL_HAND_BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double profitRate;

    GameResultType(double profitRate) {
        this.profitRate = profitRate;
    }

    public static GameResultType parse(int playerHandCount, int playerPoint, int dealerPoint) {
        if (GameRule.isBlackJack(playerHandCount, playerPoint)) {
            return WIN_WITH_INITIAL_HAND_BLACKJACK;
        }
        if (GameRule.isBust(dealerPoint)) {
            return GameResultType.WIN;
        }
        if (GameRule.isBust(playerPoint)) {
            return GameResultType.LOSE;
        }
        return parseWhenNotBust(playerPoint, dealerPoint);
    }

    public double getProfitRate() {
        return profitRate;
    }

    private static GameResultType parseWhenNotBust(int playerPoint, int dealerPoint) {
        if (playerPoint > dealerPoint) {
            return GameResultType.WIN;
        }
        if (playerPoint < dealerPoint) {
            return GameResultType.LOSE;
        }
        return GameResultType.DRAW;
    }
}
