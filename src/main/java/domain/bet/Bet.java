package domain.bet;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Bet {
    private final Map<Name, Money> bet = new LinkedHashMap<>();

    public Bet(Map<Name, Long> betHistory) {
        for (Entry<Name, Long> betEntry : betHistory.entrySet()) {
            Money money = new Money(betEntry.getValue());
            bet.put(betEntry.getKey(), money);
        }
    }

    public Map<Name, Money> getBettingLog() {
        return Map.copyOf(bet);
    }

    public BetProfit calculateProfit(Map<Name, GameResult> playerResults) {
        return BetProfit.calculateProfit(playerResults, bet);
    }
}
