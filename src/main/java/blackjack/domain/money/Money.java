package blackjack.domain.money;

public class Money {

    private static final String AMOUNT_ERROR_MESSAGE = "양수를 입력해 주세요.";
    private final int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(AMOUNT_ERROR_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
