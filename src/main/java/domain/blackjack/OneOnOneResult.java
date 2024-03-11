package domain.blackjack;

public record OneOnOneResult(int win, int lose) {
    public static final OneOnOneResult EMPTY = new OneOnOneResult(0, 0);
    public static final OneOnOneResult ONE_WIN = new OneOnOneResult(1, 0);
    public static final OneOnOneResult ONE_LOSE = new OneOnOneResult(0, 1);

    public static OneOnOneResult fromCondition(final boolean condition) {
        if (condition) {
            return ONE_WIN;
        }
        return ONE_LOSE;
    }

    public OneOnOneResult updateStatus(final OneOnOneResult oneOnOneResult) {
        if (oneOnOneResult.win > 0) {
            return addWin();
        }
        return addLose();
    }

    public OneOnOneResult addWin() {
        return new OneOnOneResult(this.win + 1, lose);
    }

    public OneOnOneResult addLose() {
        return new OneOnOneResult(this.win, lose + 1);
    }
}
