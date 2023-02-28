package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardNumber implements Comparable<CardNumber> {
    private static final int MAX_RANGE = 13;
    private static final int MIN_RANGE = 1;

    private static final List<CardNumber> cache;
    private final int value;

    static {
        cache = new ArrayList<>();
        for (int i = MIN_RANGE; i <= MAX_RANGE; i++) {
            cache.add(new CardNumber(i));
        }
    }

    private CardNumber(final int value) {
        validateRange(value);
        this.value = value;
    }

    public static CardNumber of(final int value) {
        return cache.stream()
                .filter(cardNumber -> cardNumber.value == value)
                .findAny()
                .orElseThrow(() -> new AssertionError());
    }

    private void validateRange(final int value) {
        if (value < MIN_RANGE || value > MAX_RANGE) {
            throw new IllegalArgumentException("범위를 초과하였습니다.");
        }
    }

    @Override
    public int compareTo(final CardNumber o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CardNumber that = (CardNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
