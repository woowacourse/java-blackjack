package blackjack.controller;

public final class Retry {
    private final int count;

    public Retry(final int count) {
        this.count = count;
    }

    public boolean isRepeatable() {
        return count > 0;
    }

    public Retry decrease() {
        return new Retry(count - 1);
    }

    public String getFailMessage() {
        return "재입력 횟수를 초과하였습니다.";
    }
}
