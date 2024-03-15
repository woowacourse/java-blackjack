package domain.betting;

import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class BetInfo {

    private final Map<Player, Money> betMap;

    public BetInfo(Map<Player, Money> betMap) {
        this.betMap = betMap;
    }

    public static BetInfo withNoEntry() {
        return new BetInfo(new HashMap<>());
    }

    public void add(Player player, Money betAmount) {
        betMap.put(player, betAmount);
    }

    public Money findBetAmountBy(Player player) {
        return betMap.get(player);
    }
}
