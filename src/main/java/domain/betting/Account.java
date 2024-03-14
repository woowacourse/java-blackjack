package domain.betting;

import domain.participant.Participant;

public class Account {

    private static final Money INITIAL_BALANCE = Money.valueOf(0);

    private final Participant participant;
    private Money money;

    private Account(Participant participant, Money money) {
        this.participant = participant;
        this.money = money;
    }

    public static Account from(Participant participant) {
        return new Account(participant, INITIAL_BALANCE);
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
