package blackjack.domain.result;

public class DealerResult {

    private final ResultCount win;
    private final ResultCount lose;
    private final ResultCount draw;

    public DealerResult() {
        win = new ResultCount(Result.WIN);
        lose = new ResultCount(Result.LOSE);
        draw = new ResultCount(Result.DRAW);
    }

    public void increaseWin() {
        this.win.increaseCount();
    }

    public void increaseLose() {
        this.lose.increaseCount();
    }

    public void increaseDraw() {
        this.draw.increaseCount();
    }

    public ResultCount getWin() {
        return win;
    }

    public ResultCount getLose() {
        return lose;
    }

    public ResultCount getDraw() {
        return draw;
    }
}
