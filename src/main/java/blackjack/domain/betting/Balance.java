package blackjack.domain.betting;

public class Balance {
    private final int value;

    Balance() {
        this.value = 0;
    }

    // TODO: 생성자 외부에서 사용하는 것을 막는 건 어떨까
    Balance(final int value) {
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

    public Balance add(final Balance balance) {
        return new Balance(this.value + balance.value);
    }
}
