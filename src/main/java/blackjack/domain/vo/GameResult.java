package blackjack.domain.vo;

import java.math.BigDecimal;

public record GameResult(
        String name,
        BigDecimal profit
) {
    public static GameResult from(String name, BigDecimal profit) {
        return new GameResult(name, profit);
    }
}