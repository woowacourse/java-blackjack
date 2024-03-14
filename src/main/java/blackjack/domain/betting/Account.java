package blackjack.domain.betting;

public class Account {
    private Balance balance;

    Account() {
        this.balance = new Balance();
    }

    Balance deposit(final Balance balance) {
        this.balance = this.balance.add(balance);
        return this.balance;
    }

    Balance withdraw(final Balance balance) {
        this.balance = this.balance.sub(balance);
        return this.balance;
    }
}
