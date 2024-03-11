package blackjack.domain.betting;

import java.util.Objects;

public class BetRevenue {

    private final double value;

    public BetRevenue(final double value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final BetRevenue that)) {
            return false;
        }
        return Double.compare(value, that.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
