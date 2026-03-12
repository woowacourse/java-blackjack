package domain;

import java.util.HashMap;
import java.util.Map;

public class Bet {
    private final Map<String, Integer> betting;

    public Bet() {
        betting = new HashMap<>();
    }

    public void addBetting(String name, Integer bettingAmount) {
        betting.put(name, bettingAmount);
    }

    public double calculateEarningPrize(String name, WinningCondition playerCondition) {
        if (playerCondition == WinningCondition.BLACK_JACK) {
            return betting.get(name) * 1.5;
        }

        if (playerCondition == WinningCondition.WIN) {
            return betting.get(name) * 1.0;
        }

        if (playerCondition == WinningCondition.LOSE) {
            return betting.get(name) * -1.0;
        }

        return 0;
    }
}
