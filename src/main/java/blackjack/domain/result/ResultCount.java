package blackjack.domain.result;

public class ResultCount {

    private final Result result;
    private int count;

    ResultCount(final Result result) {
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
