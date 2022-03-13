package blackjack.domain;

public class Result {
    private final boolean isWin;

    public Result(boolean isWin) {
        this.isWin = isWin;
    }

    public boolean getIsWin() {
        return isWin;
    }
}
