package blackjack.domain.participants;

public class Money {

    // 돈이 음수가 되는 경우
    // 베팅 머니는 10원단위여야함.
    private final double amount;

    public Money(final double amount) {
        this.amount = amount;
    }

    public Money() {
        this(0);
    }

    public Money add(final Money add) {
        return new Money(amount + add.amount);
    }

    public Money multiple(final double profit) {
        return new Money(amount * profit);
    }

    public double getAmount() {
        return amount;
    }

}
