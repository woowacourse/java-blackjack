package domain;

import java.util.HashMap;
import java.util.Map;

public class Bet {
    private final Map<String, Integer> betting;

    public Bet() {
        betting = new HashMap<>();
    }

    public void addBetting(String name, int bettingAmount) {
        betting.putIfAbsent(name, bettingAmount);
    }

    public int bettingAmount(String name) {
        return betting.get(name);
    }
}
