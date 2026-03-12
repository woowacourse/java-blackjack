package domain.result;

import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameProfit {
    private final Map<String, Integer> profitMoney = new LinkedHashMap<>();

    public GameProfit(Map<Player, GameResult> gameResultMap) {
        initializeMap(gameResultMap);
    }

    private void initializeMap(Map<Player, GameResult> gameResultMap) {
        for (Map.Entry<Player, GameResult> gameResultEntry : gameResultMap.entrySet()) {
            addProfitMoney(gameResultEntry.getKey(), gameResultEntry.getValue());
        }
    }

    public void addProfitMoney(Player player, GameResult gameResult) {
        double profit = gameResult.getProfit();
        int battingMoney = player.getBattingMoney();
        int battingResult = (int) (profit * battingMoney);
        profitMoney.put(player.getName(), battingResult);
    }

    public Map<String, Integer> getPlayerProfit() {
        return profitMoney;
    }

    public int getDealerProfit() {
        int dealerProfit = 0;
        for (Map.Entry<String, Integer> player : profitMoney.entrySet()) {
            dealerProfit -= player.getValue();
        }
        return dealerProfit;
    }


}
