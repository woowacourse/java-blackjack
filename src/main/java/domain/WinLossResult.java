package domain;

public enum WinLossResult {

    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    WIN_WITH_BLACK_JACK("블랙잭으로 승");

    private final String winLossMessage;

    WinLossResult(String winLossMessage) {
        this.winLossMessage = winLossMessage;
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
