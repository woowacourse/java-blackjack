package domain;

import java.math.BigDecimal;
import java.util.function.Function;

public enum PlayerResult {
    BLACK_JACK_WIN(money -> money.multiply(new BigDecimal("1.5"))),
    WIN(money -> money.multiply(new BigDecimal("1"))),
    LOSE(money -> money.multiply(new BigDecimal("-1"))),
    TIE(money -> money.multiply(new BigDecimal("0")));

    private final Function<BigDecimal,BigDecimal> calculate;

    PlayerResult(final Function<BigDecimal, BigDecimal> calculate) {
        this.calculate = calculate;
    }

    public PlayerResult reverse() {
        if (this==WIN) {
            return LOSE;
        }
        if (this==LOSE) {
            return WIN;
        }
        return TIE;
    }

    public boolean won() {
        return this == WIN;
    }

    public boolean lost() {
        return this == LOSE;
    }

    public BigDecimal earn(final Money money) {
        return this.calculate.apply(money.getValue());
    }
}
