package domain;

import static util.ExceptionConstants.ERROR_HEADER;

import java.math.BigDecimal;

public record BettingMoney(
        int bettingMoney
) {

    private static final int BETTING_UNIT = 1000;

    public BettingMoney {
        validateNegative(bettingMoney);
        validateUnit(bettingMoney);
    }

    public Profit calculateProfit(GameResultStatus gameResultStatus) {
        BigDecimal profitValue = gameResultStatus.calculateProfit(bettingMoney);
        return new Profit(profitValue);
    }

    private void validateNegative(int bettingMoney) {
        if (bettingMoney < 0) {
            throw new IllegalArgumentException(ERROR_HEADER + "베팅 금액은 음수일 수 없습니다.");
        }
    }

    private void validateUnit(int bettingMoney) {
        if (bettingMoney % BETTING_UNIT != 0) {
            throw new IllegalArgumentException(ERROR_HEADER + "베팅 금액은 1000원 단위여야 합니다.");
        }
    }
}
