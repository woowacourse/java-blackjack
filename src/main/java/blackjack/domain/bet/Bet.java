package blackjack.domain.bet;

public class Bet {

    private static final int MAX_VALUE_FOR_1_5X_LIMIT = 1431665764;

    private final int amount;

    public Bet(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 1 이상의 양수여야 합니다.");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("배팅 금액은 0 일 수 없습니다.");
        }
        if (amount > MAX_VALUE_FOR_1_5X_LIMIT) {
            throw new IllegalArgumentException(String.format("배팅 금액은 %,d 이하여야 합니다", MAX_VALUE_FOR_1_5X_LIMIT));
        }
    }

    public int calculateProfit(ProfitRate profitRate) {
        return profitRate.getProfit(amount);
    }
}
