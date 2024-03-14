package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingPot {

    private final Map<Player, Bet> betRecord = new LinkedHashMap<>();

    public void collect(Player player, Bet bet) {
        betRecord.put(player, bet);
    }
}
