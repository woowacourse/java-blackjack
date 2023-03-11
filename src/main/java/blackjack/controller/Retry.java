package blackjack.controller;

public final class Retry {

    private static final int DEFAULT_COUNT = 5;

    private int count;

    public Retry() {
        this(DEFAULT_COUNT);
    }

    public Retry(final int count) {
        this.count = count;
    }

    public void reset() {
        this.count = DEFAULT_COUNT;
    }

    public boolean isRepeatable() {
        return count > 0;
    }

    public void decrease() {
        count--;
    }

    public String getFailMessage() {
        return "재입력 횟수를 초과하였습니다.";
    }
}
