package domain;

public final class AceCount {
    private final int value;

    private AceCount(int value) {
        this.value = value;
    }

    public static AceCount zero() {
        return new AceCount(0);
    }

    public AceCount increase() {
        return new AceCount(value + 1);
    }

    public AceCount decrease() {
        return new AceCount(value - 1);
    }

    public boolean hasAny() {
        return value > 0;
    }
}
