package domain.bet;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.List;
import java.util.Map;

public class Bet {

    private final BetHistory betHistory;
    private final BetProfit betProfit;

    public Bet(List<Name> playerNames) {
        this.betHistory = new BetHistory(playerNames);
        this.betProfit = new BetProfit(playerNames);
    }

    public void bettingMoney(Name playerName, int bettingMoney) {
        betHistory.bettingMoney(playerName, bettingMoney);
    }

    public void calculateProfit(Map<Name, GameResult> playerResults) {
        betProfit.calculateProfit(playerResults, betHistory.getBetHistory());
    }

    public Map<Name, Integer> getPlayerBetProfit() {
        return betProfit.getPlayerBetProfit();
    }

    public int getDealerBetProfit() {
        return betProfit.getDealerBetProfit();
    }
}
