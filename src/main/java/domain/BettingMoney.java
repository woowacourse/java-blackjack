package domain;

import java.math.BigDecimal;

public record BettingMoney(
        int bettingMoney
) {

    public Profit calculateProfit(GameResultStatus gameResultStatus) {
        BigDecimal profitValue = gameResultStatus.calculateProfit(bettingMoney);
        return new Profit(profitValue);
    }
}
