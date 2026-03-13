package model.judgement;

public record BetAmount(int amount) {

    private static final int MIN_BET_AMOUNT = 1;
    private static final int MAX_BET_AMOUNT = 1_000_000;
    private static final double BLACKJACK_MULTIPLIER = 1.5;

    public BetAmount {
        validate(amount);
    }

    private static void validate(int amount) {
        if (amount < MIN_BET_AMOUNT || amount > MAX_BET_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("베팅 금액은 %d원 이상 %d원 이하여야 합니다.", MIN_BET_AMOUNT, MAX_BET_AMOUNT)
            );
        }
    }

    public Profit toProfit() {
        return new Profit(amount);
    }

    public Profit toNegativeProfit() {
        return new Profit(-amount);
    }

    public Profit toBlackjackProfit() {
        return new Profit((int) (amount * BLACKJACK_MULTIPLIER));
    }
}
