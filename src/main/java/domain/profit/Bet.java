package domain.profit;

public record Bet(int value) {

    public static final int MIN_BET = 1_000;
    public static final int MAX_BET = 1_000_000;

    public Bet {
        validateRange(value);
    }

    private void validateRange(int value) {
        if (value < MIN_BET || value > MAX_BET) {
            throw new IllegalArgumentException("베팅 금액은 " + MIN_BET + "이상 " + MAX_BET + "이하여야 합니다.");
        }
    }
}
