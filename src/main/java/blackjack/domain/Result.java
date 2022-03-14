package blackjack.domain;

public class Result {
    private static final int BLACK_JACK = 21;
    private final int dealerSum;
    private final int playerSum;
    private final int betting;

    public Result(int dealerSum, int playerSum, int betting) {
        this.dealerSum = dealerSum;
        this.playerSum = playerSum;
        this.betting = betting;
    }

    public int getRevenue() {
        if (isWin()) {
            if (playerSum == BLACK_JACK) {
                return betting * 3 / 2;
            }
            return betting;
        }
        if (playerSum == BLACK_JACK) {
            return 0;
        }
        return -betting;
    }

    private boolean isWin() {
        if (playerSum > BLACK_JACK) {
            return false;
        }
        if (dealerSum > BLACK_JACK) {
            return true;
        }
        return dealerSum < playerSum;
    }
}
