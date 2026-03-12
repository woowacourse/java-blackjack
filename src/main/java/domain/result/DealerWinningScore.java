package domain.result;

public record DealerWinningScore(WinOrDraw winOrDraw, int loseCount) {

    public static DealerWinningScore of(long winCount, long drawCount, long loseCount) {
        return new DealerWinningScore(new WinOrDraw(winCount, drawCount), (int) loseCount);
    }

    public int winCount() {
        return (int) this.winOrDraw().winCount();
    }

    public int drawCount() {
        return (int) this.winOrDraw().drawCount();
    }

    private record WinOrDraw(long winCount, long drawCount) {
    }
}
