package blackjack.domain.betting;

public class Account {
    private Balance balance;

    Account() {
        this.balance = new Balance();
    }

    public Balance deposit(final Balance balance) {
        this.balance = this.balance.add(balance);
        return this.balance;
    }
}
