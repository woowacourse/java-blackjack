package blackjack.domain.judgement;

import java.math.BigDecimal;

public record Profit (
        BigDecimal money
){
    public static final Profit ZERO = new Profit(BigDecimal.ZERO);

    public Profit add(Profit other) {
        return new Profit(this.money.add(other.money));
    }

    public Profit negate() {
        return new Profit(money.negate());
    }

    @Override
    public String toString() {
        return money.toString();
    }
}
