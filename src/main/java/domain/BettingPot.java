package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingPot {

    private final Map<Player, Bet> betRecord = new LinkedHashMap<>();

    public void put(Player player, Bet bet) {
        betRecord.put(player, bet);
    }

    public Map<Player, Integer> settlePlayerBet(Map<Player, PlayerGameResult> playerResults) {
        Map<Player, Integer> settlement = new LinkedHashMap<>();

        for (Map.Entry<Player, PlayerGameResult> playerResult : playerResults.entrySet()) {
            Player player = playerResult.getKey();
            PlayerGameResult playerGameResult = playerResult.getValue();
            int playerSettlement = settleAccordingToResult(player, playerGameResult);
            settlement.put(player, playerSettlement);
        }

        return settlement;
    }

    public int settleDealerBet(Map<Player, PlayerGameResult> playerResults) {
        int allPlayersWinning = 0;

        for (Map.Entry<Player, PlayerGameResult> playerResult : playerResults.entrySet()) {
            Player player = playerResult.getKey();
            PlayerGameResult playerGameResult = playerResult.getValue();
            allPlayersWinning += settleAccordingToResult(player, playerGameResult);
        }

        return allPlayersWinning * -1;
    }

    private int settleAccordingToResult(Player player, PlayerGameResult playerGameResult) {
        int playerBetAmount = getPlayerBetAmount(player);
        return (int) (playerBetAmount * playerGameResult.getWinningRate());
    }

    private int getPlayerBetAmount(Player player) {
        return betRecord.get(player).getAmount();
    }
}
