package blackjack.domain.money;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Wallet {
    private final Map<String, Money> values;

    public Wallet() {
        this.values = new HashMap<>();
    }

    public void put(String name, Money money) {
        values.put(name, money);
    }

    public Map<String, Integer> get() {
        return values.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().get()));
    }

    public int calculateSumAndNegative() {
        return values.values().stream()
                .mapToInt(Money::get)
                .sum();
    }
}
