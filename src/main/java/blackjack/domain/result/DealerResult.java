package blackjack.domain.result;

public class DealerResult {

    private final Win win;
    private final Lose lose;

    public DealerResult() {
        win = new Win();
        lose = new Lose();
    }

    public void increaseWin() {
        this.win.increaseCount();
    }

    public void increaseLose() {
        this.lose.increaseCount();
    }

    public Win getWin() {
        return win;
    }

    public Lose getLose() {
        return lose;
    }
}
