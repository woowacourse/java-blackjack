package blackjack.domain.betting;

import blackjack.domain.player.Name;

import java.util.HashMap;
import java.util.Map;

public class Casino {
    private final Map<Name, Account> accounts;

    Casino() {
        this.accounts = new HashMap<>();
    }

    void transfer(final Name name, final Cash cash) {
        final Account account = accounts.computeIfAbsent(name, ignore -> new Account());
        final Account newAccount = account.deposit(cash);
        accounts.put(name, newAccount);
    }

    Cash checkBalance(final Name name) {
        final Account account = accounts.computeIfAbsent(name, ignore -> new Account());
        return account.getBalance();
    }
}
