package domain;

public class DealerResult {

    private int winCount;
    private int tieCount;
    private int loseCount;

    public DealerResult() {
        this.winCount = 0;
        this.tieCount = 0;
        this.loseCount = 0;
    }

    public void addWinCount() {
        winCount++;
    }

    public void addTieCount() {
        tieCount++;
    }

    public void addLoseCount() {
        loseCount++;
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
