package blackjack.dto;

import blackjack.domain.Bet;
import blackjack.domain.Name;

public class PlayerGameResult {
    private final String name;
    private final int result;

    public PlayerGameResult(String name, int result) {
        this.name = name;
        this.result = result;
    }

    public static PlayerGameResult of(Name name, Bet bet) {
        return new PlayerGameResult(name.getName(), bet.getBet());
    }

    public String getName() {
        return name;
    }

    public int getResult() {
        return result;
    }
}
