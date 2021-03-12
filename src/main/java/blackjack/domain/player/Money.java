package blackjack.domain.player;

public class Money {
    public static final Money emptyMoney = new Money(0);

    private final int money;

    public Money(final int money) {
        this.money = money;
    }

    public Money add(final Money money) {
        return new Money(this.money + money.money);
    }

    public Money sub(final Money money) {
        return new Money(this.money - money.money);
    }

    public Money multiply(final double times) {
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
