package blackjack.domain;

public class Money {

    public static final int MIN_AMOUNT = 100;
    public static final int AMOUNT_UNIT = 100;

    private final int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 금액은 " + MIN_AMOUNT + "원 이상이어야 합니다.");
        }
        if (amount % AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 " + MIN_AMOUNT + "원 단위어야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
