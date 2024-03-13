package domain;

import java.util.Objects;

public class Amount {

    private final long value;

    public Amount(final long value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Amount amount)) {
            return false;
        }
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
