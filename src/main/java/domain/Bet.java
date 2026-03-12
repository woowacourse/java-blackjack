package domain;

import java.util.HashMap;
import java.util.Map;

public class Bet {
    private static final double BLACK_JACK_RATE = 1.5;
    private static final double LOSE_RATE = -1.0;

    private final Map<String, Integer> betting;

    public Bet() {
        betting = new HashMap<>();
    }

    public void addBetting(String name, int bettingAmount) {
        betting.put(name, bettingAmount);
    }

    public double calculateEarningPrize(String name, WinningCondition playerCondition) {
        if (playerCondition == WinningCondition.BLACK_JACK) {
            return betting.get(name) * BLACK_JACK_RATE;
        }

        if (playerCondition == WinningCondition.WIN) {
            return betting.get(name);
        }

        if (playerCondition == WinningCondition.LOSE) {
            return betting.get(name) * LOSE_RATE;
        }

        return 0;
    }
}
