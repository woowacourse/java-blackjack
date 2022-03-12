package blackjack.domain.result;

public class ResultCount {

    private final Result verify;
    private int count;

    ResultCount(final Result verify) {
        this.verify = verify;
    }

    public void increaseCount() {
        this.count++;
    }

    public Result getVerify() {
        return verify;
    }

    public int getCount() {
        return count;
    }
}
