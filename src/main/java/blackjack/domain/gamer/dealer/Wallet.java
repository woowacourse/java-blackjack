package blackjack.domain.gamer.dealer;

import blackjack.domain.money.Money;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private final Map<String, Money> values;

    public Wallet() {
        this.values = new HashMap<>();
    }

    public void put(String name, Money money) {
        values.put(name, money);
    }

    public Map<String, Money> get() {
        return Map.copyOf(values);
    }
}
