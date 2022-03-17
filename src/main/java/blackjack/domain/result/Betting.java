package blackjack.domain.result;

public final class Betting implements Comparable<Betting> {
    private static final String BETTING_MIN_ERROR_MESSAGE = "1 이상의 정수를 입력해주세요.";

    private static final int MIN_ACE_COUNT = 0;

    private int value;

    public Betting(final int money) {
        this.value = money;
        validateBetting(value);
    }

    private static void validateBetting(final int input) {
        if (input <= MIN_ACE_COUNT) {
            throw new IllegalArgumentException(BETTING_MIN_ERROR_MESSAGE);
        }
    }

    public Betting getMultiple(final double percentage) {
        value *= percentage;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Betting point = (Betting) o;
        return value == point.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public int compareTo(Betting other) {
        return Integer.compare(this.value, other.value);
    }
}
