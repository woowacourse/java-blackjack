package blackjack.dto;

import blackjack.domain.Bet;
import blackjack.domain.participant.Name;

public class PlayerGameResult {
    private final String name;
    private final int profit;

    public PlayerGameResult(String name, int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static PlayerGameResult of(Name name, Bet bet) {
        return new PlayerGameResult(name.getName(), bet.getBet());
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
