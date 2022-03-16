package blackjack.domain;

public class BettingMoney {
    private static final String NEGATIVE_MONEY_ERROR_MESSAGE = "베팅 금액은 음수를 입력할 수 없습니다.";
    private final int amount;

    public BettingMoney(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(NEGATIVE_MONEY_ERROR_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
