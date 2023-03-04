package domain;

public class Score {

    private final int value;
    private final boolean isBusted;

    private Score(int value, boolean isBusted) {
        this.value = value;
        this.isBusted = isBusted;
    }

    public static Score from(final int value) {
        return new Score(value, setBustStatus(value));
    }

    private static boolean setBustStatus(final int value) {
        return value > 21;
    }

    public int getValue() {
        return value;
    }

    public boolean isBusted() {
        return isBusted;
    }
}
