package blackjack.domain.participant;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Pattern;

public class Betting {

    public static final String BETTING_INVALID_FORMAT = "배팅 금액은 0이상의 숫자로 입력해주세요.";
    private static final Pattern PATTERN = Pattern.compile("^[+]?([0-9]*[.])?[0-9]+$");
    private final BigDecimal blackjackMultiplier = new BigDecimal("1.5");

    private final BigDecimal amount;

    public Betting(final String amount) {
        validate(amount);
        this.amount = new BigDecimal(amount);
    }

    private void validate(final String amount) {
        if (!PATTERN.matcher(amount).matches() || Double.parseDouble(amount) <= 0) {
            throw new IllegalArgumentException(BETTING_INVALID_FORMAT);
        }
    }

    public BigDecimal getBlackjackAmount() {
        return amount.multiply(blackjackMultiplier);
    }

    public BigDecimal getAmount() {
        return amount;
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
