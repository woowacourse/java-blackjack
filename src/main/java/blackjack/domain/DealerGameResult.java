package blackjack.domain;

public class DealerGameResult {

    private final int winCount;
    private final int loseCount;

    public DealerGameResult(int winCount, int loseCount) {
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
