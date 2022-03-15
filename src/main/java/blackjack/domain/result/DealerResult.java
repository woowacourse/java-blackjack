package blackjack.domain.result;

public class DealerResult {

    private final ResultCount win;
    private final ResultCount lose;

    public DealerResult() {
        win = new ResultCount(Result.WIN);
        lose = new ResultCount(Result.LOSE);
    }

    public void increaseWin() {
        this.win.increaseCount();
    }

    public void increaseLose() {
        this.lose.increaseCount();
    }

    public ResultCount getWin() {
        return win;
    }

    public ResultCount getLose() {
        return lose;
    }
}
