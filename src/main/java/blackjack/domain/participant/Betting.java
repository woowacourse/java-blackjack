package blackjack.domain.participant;

import java.math.BigDecimal;
import java.util.Objects;

public class Betting {

    private BigDecimal amount;

    public Betting(final String amount) {
        this.amount = new BigDecimal(amount);
    }

    public static Betting noBetting() {
        return new Betting("0");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Betting betting = (Betting) o;
        return Objects.equals(amount, betting.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
