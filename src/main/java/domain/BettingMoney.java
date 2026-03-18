package domain;

import domain.constant.Result;
import domain.game.ProceedsCalculator;

public class BettingMoney {
    private static final long MIN_BETTING_MONEY = 1L;
    private static final String INVALID_MESSAGE = "배팅 금액은 0보다 커야 합니다.";

    private final long value;

    public BettingMoney(long value) {
        validate(value);
        this.value = value;
    }

    private void validate(long value) {
        if (value < MIN_BETTING_MONEY) {
            throw new IllegalArgumentException(INVALID_MESSAGE);
        }
    }

    public double calculateProceeds(Result result) {
        return ProceedsCalculator.calculate(value, result);
    }
}