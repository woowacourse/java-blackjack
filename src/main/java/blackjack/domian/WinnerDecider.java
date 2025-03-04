package blackjack.domian;

public class WinnerDecider {

    private final int dealerScore;

    public WinnerDecider(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    public WinningResult decide(int score) {
        if (dealerScore == score) {
            return WinningResult.DRAW;
        }
        if (dealerScore > score) {
            return WinningResult.LOSE;
        }
        return null;
    }
}
