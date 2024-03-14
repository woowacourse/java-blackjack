package dto;

import domain.betting.Profit;
import domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public record ProfitDto(Map<String, Integer> gameProfits) {
    public static ProfitDto from(final Map<Player, Profit> playerProfits) {
        Map<String, Integer> gameProfits = new LinkedHashMap<>();

        for (Map.Entry<Player, Profit> entry : playerProfits.entrySet()) {
            String playerName = entry.getKey().getName().name();
            Integer profit = entry.getValue().getMoney();
            gameProfits.put(playerName, profit);
        }
        return new ProfitDto(gameProfits);
    }

    public Entry<String, Integer> getDealerProfit() {
        int playerProfitsSum = gameProfits.values().stream()
                .reduce(0, Integer::sum);
        return Map.entry("딜러", playerProfitsSum * -1);
    }
}
