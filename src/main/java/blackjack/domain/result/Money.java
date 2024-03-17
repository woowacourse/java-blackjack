package blackjack.domain.result;

public record Money(int money) {
    public Money multiply(double rate) {
        return new Money((int) (this.money * rate));
    }
}
