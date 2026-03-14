package domain;

public record BetMoney(int betAmount) {
    public BetMoney {
        validateIsPositive(betAmount);
    }

    private void validateIsPositive(int betAmount) {
        if (betAmount <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }

    public int getBetAmount() {
        return betAmount;
    }
}
