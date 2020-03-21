package domain.game;

import java.util.HashMap;
import java.util.Map;

public class Moneys {
    private final Map<String, Money> moneys;

    public Moneys() {
        moneys = new HashMap<>();
    }

    public void add(final String name, final Money money) {
        moneys.put(name, money);
    }

    public Money getValue(String name) {
        return moneys.get(name);
    }
}
