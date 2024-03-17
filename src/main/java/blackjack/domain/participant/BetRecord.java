package blackjack.domain.participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetRecord {
    private final Map<Name, AmountOfBet> betRecord;

    public BetRecord(Map<Name, AmountOfBet> betRecord) {
        this.betRecord = betRecord;
    }

    public ProfitDetails calculateProfit(Players players, Dealer dealer) {
        List<Player> playerList = players.getPlayers();
        Map<Name, Profit> profitDetails = new LinkedHashMap<>();
        for (Player player : playerList) {
            AmountOfBet betAmountOfBet = betRecord.get(player.getName());
            double profitRate = player.calculateProfitRate(dealer.getHand());
            profitDetails.put(player.getName(), Profit.from(betAmountOfBet).multiplication(profitRate));
        }
        return new ProfitDetails(profitDetails);
    }
}
