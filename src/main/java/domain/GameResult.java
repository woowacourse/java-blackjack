package domain;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    PUSH("무"),
    NONE("없음");

    private final String name;

    GameResult(String name) {
        this.name = name;
    }

    public static GameResult calculateDealerGameResult(Dealer dealer, Player player) {
        GameScore dealerScore = dealer.calculateScore();
        GameScore playerScore = player.calculateScore();
        if (isDealerWinning(dealerScore, playerScore)) {
            return WIN;
        }
        if (isDealerLosing(dealerScore, playerScore)) {
            return LOSE;
        }
        return PUSH;
    }

    public static GameResult getOppositeResult(GameResult gameResult) {
        if (gameResult == WIN) {
            return LOSE;
        }
        if (gameResult == LOSE) {
            return WIN;
        }
        return PUSH;
    }

    public String getName() {
        return name;
    }

    private static boolean isDealerWinning(GameScore dealerScore, GameScore playerScore) {
        return playerScore.isBust()
                || (dealerScore.isGreaterThan(playerScore) && !dealerScore.isBust());
    }

    private static boolean isDealerLosing(GameScore dealerScore, GameScore playerScore) {
        return dealerScore.isBust()
                || playerScore.isGreaterThan(dealerScore);
    }
}
