package blackjack.domain;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String text;

    GameResult(String text) {
        this.text = text;
    }

    public static GameResult playerResultFrom(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }
        if (player.isBlackjack() || dealer.isBust()) {
            return WIN;
        }
        return getGameResultFromOthers(dealer, player);
    }

    private static GameResult getGameResultFromOthers(Dealer dealer, Player player) {
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
