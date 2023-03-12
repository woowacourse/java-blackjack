package blackjack.controller;

public final class Retry {

    private final int initialCount;
    private int count;

    public Retry(final int count) {
        this.initialCount = count;
        this.count = count;
    }

    public void reset() {
        count = initialCount;
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
