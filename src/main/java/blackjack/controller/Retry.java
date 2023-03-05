package blackjack.controller;

public class Retry {

    private static final int DEFAULT_COUNT = 5;
    static final String FAIL_MESSAGE = "재입력 횟수를 초과하였습니다.";

    private int count;

    public Retry() {
        this(DEFAULT_COUNT);
    }

    public Retry(final int count) {
        this.count = count;
    }

    public boolean isRepeatable() {
        return count > 0;
    }

    public void decrease() {
        count--;
    }

    public String getFailMessage() {
        return FAIL_MESSAGE;
    }
}
