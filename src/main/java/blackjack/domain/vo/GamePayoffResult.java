package blackjack.domain.vo;

import java.math.BigDecimal;

public record GamePayoffResult(
        String name,
        BigDecimal profit
) {
    public static GamePayoffResult from(String name, BigDecimal profit) {
        return new GamePayoffResult(name, profit);
    }
}