package dto;

import domain.betting.Profit;
import domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public record ProfitDto(Map<String, Integer> playerProfits, Entry<String, Integer> dealerProfit) {
    public static ProfitDto from(final Map<Player, Profit> playerProfits, final Entry<Player, Integer> dealerProfit) {
        return new ProfitDto(makePlayerProfits(playerProfits), makeDealerProfit(dealerProfit));
    }

    private static Map<String, Integer> makePlayerProfits(final Map<Player, Profit> playerProfits) {
        Map<String, Integer> playerProfitsInformation = new LinkedHashMap<>();

        playerProfits.forEach((key, value) -> {
            String playerName = key.getName().name();
            Integer profit = value.getMoney();
            playerProfitsInformation.put(playerName, profit);
        });

        return playerProfitsInformation;
    }

    private static Entry<String, Integer> makeDealerProfit(Entry<Player, Integer> dealerProfitInformation) {
        return Map.entry(dealerProfitInformation.getKey().getName().name(), dealerProfitInformation.getValue());
    }
}
