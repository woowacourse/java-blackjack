package domain.betting;

import domain.participant.Players;
import java.util.List;

public class Accounts {

    private final List<Account> accounts;

    public Accounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public static Accounts from(Players players) {
        return new Accounts(players.getPlayers()
            .stream()
            .map(Account::from)
            .toList());
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
