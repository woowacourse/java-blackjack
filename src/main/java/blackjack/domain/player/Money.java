package blackjack.domain.player;

public class Money {
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public Money add(Money money) {
        return new Money(this.money + money.money);
    }

    public Money sub(Money money) {
        return new Money(this.money - money.money);
    }

    public Money multiply(double times) {
        return new Money((int) (this.money * times));
    }

    public Money abs() {
        return new Money(Math.abs(money));
    }

    public int getValue(){
        return money;
    }

    @Override
    public String toString() {
        return money + "";
    }
}
