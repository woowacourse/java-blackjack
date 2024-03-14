package blackjack.domain.betting;

public class Account {
    final private Cash balance;

    Account() {
        this.balance = new Cash();
    }

    private Account(final Cash cash) {
        this.balance = cash;
    }

    Account deposit(final Cash cash) {
        final Cash newCash = this.balance.add(cash);
        return new Account(newCash);
    }

    Account withdraw(final Cash cash) {
        final Cash newCash = this.balance.sub(cash);
        return new Account(newCash);
    }

    Cash getBalance() {
        return this.balance;
    }
}
