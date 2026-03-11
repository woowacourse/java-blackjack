package domain.participant;

import java.util.LinkedHashMap;
import java.util.Map;

public class BetMap {
    private final Map<String, Bet> betsMap = new LinkedHashMap<>();

    public void addBetAmountOf(String playerName, Bet bet) {
        betsMap.put(playerName, bet);
    }

    public Bet findBet(String playerName) {
        return betsMap.get(playerName);
    }
}
