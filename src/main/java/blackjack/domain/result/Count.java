package blackjack.domain.result;

import java.util.Objects;

public class Count {
    private final int value;

    public Count(final int value) {
        this.value = value;
    }

    public int toInt() {
        return this.value;
    }

    public Count increment() {
        return new Count(this.value + 1);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (!(object instanceof final Count count)) return false;
        return this.value == count.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
