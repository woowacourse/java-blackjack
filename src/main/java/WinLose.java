public class WinLose {

    private int winCount;
    private int loseCount;

    public WinLose() {
        this.winCount = 0;
        this.loseCount = 0;
    }

    public void decideWinningState(int myScore, int yourScore) {
        if (myScore > yourScore) {
            winCount++;
        }
        if (myScore < yourScore) {
            loseCount++;
        }
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
