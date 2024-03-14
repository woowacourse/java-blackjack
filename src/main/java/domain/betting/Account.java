package domain.betting;

import domain.participant.Player;

public class Account {

    private static final Money INITIAL_BALANCE = Money.valueOf(0);

    private final Player player;
    private Money money;

    private Account(Player player, Money money) {
        this.player = player;
        this.money = money;
    }

    public static Account from(Player player) {
        return new Account(player, INITIAL_BALANCE);
    }

    public void deposit(Money amount) {
        money = money.add(amount);
    }

    public void withdraw(Money amount) {
        money = money.subtract(amount);
    }

    public Money balance() {
        return money;
    }
}
