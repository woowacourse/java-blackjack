package domain;

public class DealerResult {

    private final int winCount;
    private final int tieCount;
    private final int loseCount;

    public DealerResult() {
        this.winCount = 0;
        this.tieCount = 0;
        this.loseCount = 0;
    }

    private DealerResult(final int winCount, final int tieCount, final int loseCount) {
        this.winCount = winCount;
        this.tieCount = tieCount;
        this.loseCount = loseCount;
    }

    public DealerResult addResult(final Result result) {
        if (result.won()) {
            return new DealerResult(winCount + 1, tieCount, loseCount);
        }
        if (result.lost()) {
            return new DealerResult(winCount, tieCount, loseCount + 1);
        }
        return new DealerResult(winCount, tieCount + 1, loseCount);
    }

    public int getWinCount() {
        return winCount;
    }

    public int getTieCount() {
        return tieCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
