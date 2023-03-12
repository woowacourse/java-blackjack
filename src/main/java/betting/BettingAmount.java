package betting;

import blackjackgame.Result;

public class BettingAmount {
    private static final int MIN_AMOUNT_UNIT = 100;
    private static final int MAX_AMOUNT_UNIT = 100_000;
    private final int amount;

    public BettingAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validateAmount(amount);
        validateUnit(amount);
    }

    private void validateUnit(int amount) {
        if (amount % MIN_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("100원 단위로 입력 가능합니다.");
        }
    }

    private void validateAmount(int amount) {
        if (amount < MIN_AMOUNT_UNIT || amount > MAX_AMOUNT_UNIT) {
            throw new IllegalArgumentException("100 이상 100,000 이하의 정수만 입력 가능합니다.");
        }
    }

    public int calculateRewardByResult(Result result) {
        if (result == null) {
            throw new IllegalArgumentException("결과가 생성되지 않았습니다.");
        }
        return (int) (result.getRate() * amount);
    }

    public int getAmount() {
        return amount;
    }
}
