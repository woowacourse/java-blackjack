package domain.game;

public class Result {
    private static final int ONE = 1;
    private final String name;
    private int winCount;
    private int loseCount;

    public Result(final String name) {
        this.name = name;
        this.winCount = 0;
        this.loseCount = 0;
    }

    public void addWinCount() {
        winCount++;
    }

    public void addLoseCount() {
        loseCount++;
    }

    public String getName() {
        return name;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public boolean isPlayCountMoreThanOne() {
        return winCount + loseCount > ONE;
    }

    public boolean hasWin() {
        return winCount >= ONE;
    }
}
