package domain;

import java.math.BigDecimal;

public record BettingMoney(
        int bettingMoney
) {

    public Profit calculateProfit(GameResultStatus gameResultStatus) {
        BigDecimal money = BigDecimal.valueOf(bettingMoney);
        BigDecimal payout = BigDecimal.valueOf(gameResultStatus.getPayout());
        return new Profit(money.multiply(payout));
    }
}
