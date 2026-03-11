package domain.participant;

import domain.result.MatchCase;

import java.util.LinkedHashMap;
import java.util.Map;

public class BetMap {
    private final Map<String, Bet> betsMap = new LinkedHashMap<>();

    public void addBetAmountOf(String playerName, Bet bet) {
        betsMap.put(playerName, bet);
    }

    public Bet findBet(String playerName) {
        return betsMap.get(playerName);
    }

    public long calculateProfit(String playerName, MatchCase matchCase) {
        Bet playerBet = this.findBet(playerName);
        return Math.round(playerBet.amount() * matchCase.getBenefitRate());
    }
}
