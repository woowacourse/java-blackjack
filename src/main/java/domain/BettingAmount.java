package domain;

public record BettingAmount(int amount) {

    private static final int MINIMUM_BETTING_AMOUNT = 10_000;

    public BettingAmount {
        validateRange(amount);
    }

    private void validateRange(int amount) {
        if (amount < MINIMUM_BETTING_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 최소 10,000원부터 입력 가능합니다.");
        }
    }
}
