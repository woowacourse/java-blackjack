package domain.result;

public enum WinLossResult {

    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    NONE("");

    private final String winLossMessage;

    WinLossResult(final String winLossMessage) {
        this.winLossMessage = winLossMessage;
    }

    public static WinLossResult of(final int winLossOption) {
        if (winLossOption == 1) {
            return WIN;
        }
        if (winLossOption == -1) {
            return LOSS;
        }
        if (winLossOption == 0) {
            return DRAW;
        }
        return NONE;
    }

    public String getWinLossMessage() {
        return winLossMessage;
    }
}
