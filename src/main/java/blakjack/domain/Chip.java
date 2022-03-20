package blakjack.domain;

public final class Chip {
    private final int value;

    public Chip(final int money) {
        this.value = money;
    }

    public int getValue() {
        return value;
    }
}
