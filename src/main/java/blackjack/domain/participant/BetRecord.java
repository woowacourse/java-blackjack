package blackjack.domain.participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetRecord {
    private final Map<Name, BetAmount> betRecord;

    public BetRecord(Map<Name, BetAmount> betRecord) {
        this.betRecord = betRecord;
    }

    public ProfitRecord calculateProfit(Players players, Dealer dealer) {
        List<Player> playerList = players.getPlayers();
        Map<Name, ProfitAmount> profitDetails = new LinkedHashMap<>();
        for (Player player : playerList) {
            BetAmount betBetAmount = betRecord.get(player.getName());
            double profitRate = player.calculateProfitRate(dealer.getHand());
            profitDetails.put(player.getName(), ProfitAmount.from(betBetAmount).multiplication(profitRate));
        }
        return new ProfitRecord(profitDetails);
    }
}
