package blackjack.model.betting;

import blackjack.model.participant.Player;
import java.util.Objects;

public class BettingAccount {
    private final Player player;
    private Money money;

    public BettingAccount(final Player player, final Money money) {
        this.player = player;
        this.money = money;
    }

    public void receive(final int profit) {
        this.money = money.add(profit);
    }

    public void withdraw(final int expense) {
        this.money = money.subtract(expense);
    }

    public Money getMoney() {
        return money;
    }

    public boolean isOwnedBy(final Player player) {
        return Objects.equals(this.player, player);
    }
}
