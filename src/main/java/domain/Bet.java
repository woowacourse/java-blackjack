package domain;

import java.util.HashMap;
import java.util.Map;

public class Bet {
    private static final String DUPLICATE_BETTING = "베팅은 중복할 수 없습니다.";
    private final Map<String, Integer> betting;

    public Bet() {
        betting = new HashMap<>();
    }

    public void addBetting(String name, int bettingAmount) {
        if (certainKey(name)) {
            throw new IllegalArgumentException(DUPLICATE_BETTING);
        }
        betting.putIfAbsent(name, bettingAmount);
    }

    public int bettingAmount(String name) {
        return betting.get(name);
    }

    private boolean certainKey(String name) {
        return betting.containsKey(name);
    }
}
