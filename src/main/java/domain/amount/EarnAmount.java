package domain.amount;

import java.util.Objects;

public class EarnAmount {

    private final long value;

    public EarnAmount(final long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EarnAmount earnAmount)) {
            return false;
        }
        return Objects.equals(value, earnAmount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
