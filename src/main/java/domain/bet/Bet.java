package domain.bet;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.List;
import java.util.Map;

public class Bet {

    private final BetHistory betHistory;

    public Bet(List<Name> playerNames) {
        this.betHistory = new BetHistory(playerNames);
    }

    public void bettingMoney(Name playerName, int bettingMoney) {
        betHistory.bettingMoney(playerName, bettingMoney);
    }

    public BetProfit calculateProfit(Map<Name, GameResult> playerResults) {
        return BetProfit.calculateProfit(playerResults, betHistory.getBetHistory());
    }
}
