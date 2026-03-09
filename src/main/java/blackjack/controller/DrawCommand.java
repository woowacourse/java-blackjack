package blackjack.controller;

public enum DrawCommand {
    HIT("y"),
    STAY("n");

    private final String value;

    DrawCommand(String value) {
        this.value = value;
    }

    public static DrawCommand of(String value) {
        if (HIT.value.equals(value)) {
            return HIT;
        }
        if (STAY.value.equals(value)) {
            return STAY;
        }
        throw new IllegalArgumentException("y 또는 n만 입력 가능합니다: " + value);
    }

    public boolean isHit() {
        return this == HIT;
    }

    public String getValue() {
        return value;
    }
}