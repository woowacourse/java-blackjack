package blackjack.domain.player;

public class ResultCount {

    private final Result result;
    private int count;

    public ResultCount(final Result result) {
        this.result = result;
    }

    public void increaseCount() {
        this.count++;
    }

    public Result getResult() {
        return result;
    }

    public int getCount() {
        return count;
    }
}
