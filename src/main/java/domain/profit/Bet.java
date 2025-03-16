package domain.profit;

public record Bet(int value) {

    public static final int MIN_BET = 1_000;
    public static final int MAX_BET = 1_000_000;
    private static final Bet DEFAULT_BET = new Bet(MIN_BET);

    public Bet {
        validateRange(value);
    }

    public static Bet defaultBet() {
        return DEFAULT_BET;
    }

    private void validateRange(int value) {
        if (value < MIN_BET || value > MAX_BET) {
            throw new IllegalArgumentException("베팅 금액은 " + MIN_BET + "이상 " + MAX_BET + "이하여야 합니다.");
        }
    }
}
