package blackjack.domain;

public class Money {
    private int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public void add(int amount) {
        validate(amount);
        this.amount += amount;
    }

    private void validate(int amount) {
        if (amount % 10 != 0) {
            throw new IllegalArgumentException("10원 단위의 금액을 입력해주세요.");
        }
    }
}
