package blackjack.domain.result;

public record Money(int money) {
    public Money add(Money other) {
        return new Money(this.money + other.money);
    }

    public Money sub(Money other) {
        return new Money(this.money - other.money);
    }

    public Money multiply(double rate) {
        return new Money((int) (this.money * rate));
    }
}
