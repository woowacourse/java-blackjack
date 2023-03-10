package blackjack.domain;

import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Bank {

    private final Map<Player, Money> bank;

    public Bank(final Map<Player, Money> bank) {
        this.bank = bank;
    }

    public Bank() {
        this.bank = new HashMap<>();
    }

    public Bank betMoney(final Player player, final Money money) {
        Money defaultMoney = bank.getOrDefault(player, new Money(0));
        bank.put(player, defaultMoney.add(money));

        return new Bank(bank);
    }

    public Money findMoney(final Player player) {
        return bank.get(player);
    }

    public Map<Player, Money> getBank() {
        return Collections.unmodifiableMap(bank);
    }
}
