package domain;

public record Bet(int amount) {
    private static final int MINIMUM_BETTING_AMOUNT = 1;

    public Bet {
        validate(amount);
    }

    private void validate(int amount) {
        if (amount < MINIMUM_BETTING_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 최소 1원 이상이어야 합니다.");
        }
    }
}
