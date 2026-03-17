package domain.bet;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Bet {
    private final Map<Name, Money> bettingLog = new LinkedHashMap<>();

    public Bet(Map<Name, Long> bettingLog) {
        for (Entry<Name, Long> betEntry : bettingLog.entrySet()) {
            Money money = new Money(betEntry.getValue());
            this.bettingLog.put(betEntry.getKey(), money);
        }
    }

    public Map<Name, Money> getBettingLog() {
        return Map.copyOf(bettingLog);
    }

    public BetProfit calculateProfit(Map<Name, GameResult> playerResults) {
        return BetProfit.calculateProfit(playerResults, bettingLog);
    }
}
