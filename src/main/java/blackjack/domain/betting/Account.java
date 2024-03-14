package blackjack.domain.betting;

public class Account {
    private Cash balance;

    Account() {
        this.balance = new Cash();
    }

    Cash deposit(final Cash cash) {
        this.balance = this.balance.add(cash);
        return this.balance;
    }

    Cash withdraw(final Cash cash) {
        this.balance = this.balance.sub(cash);
        return this.balance;
    }
}
