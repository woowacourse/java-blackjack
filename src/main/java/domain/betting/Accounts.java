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

    public Account findByPlayer(Player player) {
        return accounts.stream()
            .filter(account -> account.getPlayer() == player)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] Player를 찾을 수 없습니다."));
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
