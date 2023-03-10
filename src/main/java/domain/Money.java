package domain;

public class Money {

    private final int amount;

    public Money(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private static void validateAmount(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액을 1원 이상 설정해 주세요");
        }
    }

    public int getAmount() {
        return amount;
    }
}
