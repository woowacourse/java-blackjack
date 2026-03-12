package domain;

import java.util.HashMap;
import java.util.Map;

public class Bet {
    private static final double BLACK_JACK_RATE = 1.5;
    private static final double LOSE_RATE = -1.0;

    private final Map<Participant, Integer> betting;

    public Bet() {
        betting = new HashMap<>();
    }

    public void addBetting(Participant player, int bettingAmount) {
        betting.put(player, bettingAmount);
    }

    public double calculateEarningPrize(Participant player, WinningCondition playerCondition) {
        if (playerCondition == WinningCondition.BLACK_JACK) {
            return betting.get(player) * BLACK_JACK_RATE;
        }

        if (playerCondition == WinningCondition.WIN) {
            return betting.get(player);
        }

        if (playerCondition == WinningCondition.LOSE) {
            return betting.get(player) * LOSE_RATE;
        }

        return 0;
    }
}
