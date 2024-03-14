package domain.betting;

import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class Accounts {

    private final List<Account> accounts;

    public Accounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public static Accounts withNoAccount() {
        return new Accounts(new ArrayList<>());
    }

    public void add(Player player, Money money) {
        accounts.add(new Account(player, money));
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
