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
        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();
        if (player.isBust()) {
            return WIN;
        }
        if (dealer.isBust()) {
            return LOSE;
        }
        if (dealerScore > playerScore) {
            return WIN;
        }
        if (dealerScore < playerScore) {
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
}
