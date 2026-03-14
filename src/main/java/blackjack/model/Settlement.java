package blackjack.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Settlement {

    private final Map<Player, Profit> playerProfits;
    private final Profit dealerProfit;

    public Settlement(Map<Player, GameResult> results) {
        this.playerProfits = calculatePlayerProfits(results);
        this.dealerProfit = calculateDealerProfit(this.playerProfits);
    }

    private Map<Player, Profit> calculatePlayerProfits(Map<Player, GameResult> results) {
        Map<Player, Profit> profits = new LinkedHashMap<>();
        for (Player player : results.keySet()) {
            profits.put(player, player.calculateProfit(results.get(player)));
        }
        return profits;
    }

    private Profit calculateDealerProfit(Map<Player, Profit> playerProfits) {
        Profit total = new Profit(0);
        for (Profit profit : playerProfits.values()) {
            total = total.add(profit.negate());
        }
        return total;
    }

    // 밖에서 정산 결과를 달라고 할 때 주는 Getter (DTO 변환용)
    public Map<Player, Profit> getPlayerProfits() {
        return playerProfits;
    }

    public Profit getDealerProfit() {
        return dealerProfit;
    }
}
