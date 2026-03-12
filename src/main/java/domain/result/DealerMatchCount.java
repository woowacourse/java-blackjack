package domain.result;

public class DealerMatchCount {
    private int winningCount = 0;
    private int drawCount = 0;
    private int loseCount = 0;

    public void increaseWinCount() {
        this.winningCount++;
    }

    public void increaseDrawCount() {
        this.drawCount++;
    }

    public void increaseLoseCount() {
        this.loseCount++;
    }
}
