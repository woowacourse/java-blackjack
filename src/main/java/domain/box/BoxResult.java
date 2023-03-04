package domain.box;

public class BoxResult {

    private static final int WIN = 1;
    private static final int DRAW = 0;
    private static final int LOSE = -1;
    private final int winCount;
    private final int loseCount;

    public BoxResult(int winCount, int loseCount) {
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public static BoxResult from(int compareResult) {
        if (compareResult == WIN) {
            return new BoxResult(1, 0);
        }
        if (compareResult == DRAW) {
            return new BoxResult(0, 0);
        }
        if (compareResult == LOSE) {
            return new BoxResult(0, 1);
        }
        throw new IllegalArgumentException("올바르지 않은 비교값입니다.");
    }

    public BoxResult addReversed(BoxResult result) {
        return new BoxResult(this.winCount + result.loseCount, this.loseCount + result.winCount);
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}
