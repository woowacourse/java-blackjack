package blackjack.domain.betting;

import java.util.HashMap;
import java.util.Map;

public class BettingAmounts {

    // TODO: key는 String(PlayerName)이 좋을까? Player가 좋을까?
    private final Map<String, Integer> bettingAmounts;

    public BettingAmounts() {
        this.bettingAmounts = new HashMap<>();
    }

    public void put(String playerName, Integer bettingAmount) {
        bettingAmounts.put(playerName, bettingAmount);
    }

    public Integer findByName(String name) {
        return bettingAmounts.get(name);
    }

}
