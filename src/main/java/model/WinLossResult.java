package model;

public enum WinLossResult {

    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    WIN_WITH_BLACK_JACK("블랙잭으로 승");

    private final String winLossMessage;

    WinLossResult(String winLossMessage) {
        this.winLossMessage = winLossMessage;
    }

    public static WinLossResult computeWinLoss(Player player, Dealer dealer) {
        int playerScore = player.getHandTotal();
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return WinLossResult.WIN_WITH_BLACK_JACK;
        }
        if (playerScore > dealer.getHandTotal()) {
            return WinLossResult.WIN;
        }
        if (playerScore == dealer.getHandTotal()) {
            return WinLossResult.DRAW;
        }
        return WinLossResult.LOSS;
    }

    public static WinLossResult of(int winLossOption) {
        if (winLossOption == 1) {
            return WIN;
        }
        if (winLossOption == -1) {
            return LOSS;
        }
        if (winLossOption == 0) {
            return DRAW;
        }
        if (winLossOption == 2) {
            return WIN_WITH_BLACK_JACK;
        }
        throw new IllegalArgumentException("Invalid winLossOption: " + winLossOption);
    }

    public String getWinLossMessage() {
        return winLossMessage;
    }
}
