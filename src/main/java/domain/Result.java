package domain;

public class Result {
    private User user;
    private int winCount;
    private int loseCount;

    public Result(User user, int winCount, int loseCount) {
        this.user = user;
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public void addWinCount() {
        winCount++;
    }

    public void addLoseCount() {
        loseCount++;
    }

    @Override
    public String toString() {
        return "user=" + user.getName() +
                ", win" + winCount + ", lose" + loseCount;
    }
}
