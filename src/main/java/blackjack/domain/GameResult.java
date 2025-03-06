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
        BlackjackState dealerState = BlackjackState.of(dealer);
        BlackjackState playerState = BlackjackState.of(player);

        if (playerState == BlackjackState.BUST) {
            return LOSE;
        }
        if (dealerState == BlackjackState.BLACKJACK && playerState == BlackjackState.BLACKJACK) {
            return DRAW;
        }
        if (dealerState == BlackjackState.BUST || playerState == BlackjackState.BLACKJACK) {
            return WIN;
        }
        return getGameResultFromOthers(dealer, player);
    }

    private static GameResult getGameResultFromOthers(Dealer dealer, Player player) {
        int dealerSum = dealer.calculateDenominations();
        int playerSum = player.calculateDenominations();

        if (dealerSum < playerSum) {
            return GameResult.WIN;
        }
        if (dealerSum == playerSum) {
            return DRAW;
        }
        return GameResult.LOSE;
    }

    public String getText() {
        return text;
    }
}
