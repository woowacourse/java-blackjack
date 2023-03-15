package domain.result;

import java.math.BigDecimal;

public enum WinningStatus {
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final BigDecimal multiplier;

    WinningStatus(final int multiplier) {
        this.multiplier = BigDecimal.valueOf(multiplier);
    }

    public WinningStatus reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public BigDecimal multiplier() {
        return multiplier;
    }
}
