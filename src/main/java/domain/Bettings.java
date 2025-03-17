package domain;

import java.util.HashMap;
import java.util.Map;

public class Bettings {

    private final Map<String, Integer> bettings;

    public Bettings() {
        this.bettings = new HashMap<>();
    }

    public void registerBetting(String playerName, int bettingMoney) {
        this.bettings.put(playerName, bettingMoney);
    }

    public int findBettingOfPlayer(String playerName) {
        return this.bettings.get(playerName);
    }

    public int calculateDealerBetting() {
        return this.bettings.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum() * (-1);
    }
}
