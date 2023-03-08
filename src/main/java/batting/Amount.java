package batting;

import blackjackgame.Result;

public class Amount {
    public static final int MIN_AMOUNT_UNIT = 100;
    private final int amount;

    public Amount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validateNegative(amount);
        validateUnit(amount);
    }

    private void validateUnit(int amount) {
        if (amount % MIN_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("100원 단위로 입력 가능합니다.");
        }
    }

    private void validateNegative(int amount) {
        if (amount < MIN_AMOUNT_UNIT) {
            throw new IllegalArgumentException("100이상의 정수만 입력 가능합니다.");
        }
    }

    public int calculateRewardByResult(Result result) {
        if (result.equals(Result.WIN)) {
            return amount * 2;
        }
        if (result.equals(Result.LOSE)) {
            return amount * -2;
        }
        return amount;
    }
}
