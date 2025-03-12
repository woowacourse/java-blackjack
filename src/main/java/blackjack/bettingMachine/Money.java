package blackjack.bettingMachine;

public class Money {

    private long money;

    public Money(final long money) {
        this.money = money;
    }

    public void increase(final long amount) {
        this.money += amount;
    }
}
