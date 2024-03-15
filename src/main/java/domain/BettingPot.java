package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingPot {

    private final Map<Player, Bet> betRecord = new LinkedHashMap<>();

    public void collect(Player player, Bet bet) {
        betRecord.put(player, bet);
    }

    public Map<Player, Integer> settle(Map<Player, Result> playerResults) {
        Map<Player, Integer> settlement = new LinkedHashMap<>();
        for (Map.Entry<Player, Result> playerResult : playerResults.entrySet()) {
            Player player = playerResult.getKey();
            int amount = betRecord.get(player).getAmount();
            amount = calculateAmount(playerResult.getValue(), amount);
            settlement.put(player, amount);
        }

        return settlement;
    }

    private int calculateAmount(Result result, int amount) {
        if (result == Result.DEALER_WIN) {
            return amount * -1;
        }
        if (result == Result.PLAYER_WIN) {
            return amount * 2;
        }
        return amount;
    }
}
