package blackjack.bettingMachine;

public class Money {

    private long money;

    public Money(final long money) {
        this.money = money;
    }

    public void add(final long amount) {
        this.money += amount;
    }

    public long getMoney() {
        return money;
    }
}
