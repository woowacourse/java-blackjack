package domain;

public record BetMoney(int betAmount) {
    private static final int UNIT = 10;

    public BetMoney {
        validateIsPositive(betAmount);
        validateIsDividable(betAmount);
    }

    private void validateIsPositive(int betAmount) {
        if (betAmount <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }

    private void validateIsDividable(int betAmount) {
        if (betAmount % UNIT != 0) {
            throw new IllegalArgumentException("배팅 금액은 10원 단위로만 입력하실 수 있습니다.");
        }
    }

    public long calculateBettingProfit(double returnRate) {
        return (long) (betAmount * returnRate);
    }
}
