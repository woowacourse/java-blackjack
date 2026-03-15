package blackjack.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Bet {
    private final BigDecimal amount;

    private Bet(BigDecimal amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(BigDecimal amount) {
        Objects.requireNonNull(amount, "배팅 금액은 null일 수 없습니다.");
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수가 나올 수 없습니다.");
        }
    }

    public static Bet init(){
        return new Bet(BigDecimal.ZERO);
    }

    public static Bet of(String amount){
        return new Bet(new BigDecimal(amount));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Bet bet)) return false;
        return Objects.equals(amount, bet.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
