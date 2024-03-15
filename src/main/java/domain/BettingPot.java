package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingPot {

    private final Map<Player, Bet> betRecord = new LinkedHashMap<>();

    public void collect(Player player, Bet bet) {
        betRecord.put(player, bet);
    }

    public Map<Player, Integer> settlePlayer(Map<Player, Result> playerResults) {
        Map<Player, Integer> settlement = new LinkedHashMap<>();
        for (Map.Entry<Player, Result> playerResult : playerResults.entrySet()) {
            Player player = playerResult.getKey();
            int amount = betRecord.get(player).getAmount();
            amount = calculateAmount(playerResult.getValue(), amount);
            settlement.put(player, amount);
        }

        return settlement;
    }

    public int settleDealer(Map<Player, Result> playerResults) {
        int allPlayersWinning = 0;
        for (Map.Entry<Player, Result> playerResult : playerResults.entrySet()) {
            Player player = playerResult.getKey();
            int amount = betRecord.get(player).getAmount();
            allPlayersWinning += calculateAmount(playerResult.getValue(), amount);
        }

        return allPlayersWinning * -1;
    }

    private int calculateAmount(Result result, int amount) {
        return (int) (amount * result.getPlayerWinningRate());
    }
}
