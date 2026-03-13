package blackjack.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BettingAmount {
    private final BigDecimal bettingAmount;

    public BettingAmount(BigDecimal bettingAmount) {
        validate(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    public BigDecimal calculateEarningAmount(GameResult gameResult) {
        return gameResult.getEarningRate().multiply(bettingAmount).setScale(0, RoundingMode.DOWN);
    }

    private void validate(BigDecimal bettingAmount) {
        if (bettingAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("[ERROR]: 0원 초과의 값을 입력하세요.");
        }
    }
}
