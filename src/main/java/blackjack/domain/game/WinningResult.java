package blackjack.domain.game;

import java.math.BigDecimal;

public enum WinningResult {

    WIN(new BigDecimal("1")),
    BLACKJACK(new BigDecimal("1.5")),
    LOSE(new BigDecimal("-1")),
    PUSH(new BigDecimal("0"));

    final BigDecimal magnification;

    WinningResult(final BigDecimal magnification) {
        this.magnification = magnification;
    }

    public BigDecimal getMagnification() {
        return magnification;
    }
}
