package blackjack.domain.betting;

public class Balance {
    private final int value;

    public Balance(final int value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Balance balance = (Balance) o;

        return value == balance.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
