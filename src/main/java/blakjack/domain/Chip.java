package blakjack.domain;

public final class Chip {
    private static final String INVALID_MESSAGE = "1000원 단위로 생성해주세요.";
    private static final int MIN = 1000;

    private final int value;

    public Chip(final int money) {
        if (money < MIN || money % MIN != 0) {
            throw new IllegalArgumentException(INVALID_MESSAGE);
        }
        this.value = money;
    }

    public int getValue() {
        return value;
    }
}
