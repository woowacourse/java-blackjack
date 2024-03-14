package blackjack.domain.betting;

public class Cash {
    private final int value;

    Cash() {
        this.value = 0;
    }

    // TODO: 생성자 외부에서 사용하는 것을 막는 건 어떨까
    Cash(final int value) {
        this.value = value;
    }

    Cash add(final Cash cash) {
        return new Cash(this.value + cash.value);
    }

    Cash sub(final Cash cash) {
        return new Cash(this.value - cash.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Cash cash = (Cash) o;

        return value == cash.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
