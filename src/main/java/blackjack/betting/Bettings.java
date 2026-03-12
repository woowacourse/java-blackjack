package blackjack.domain.betting;

import java.util.HashMap;
import java.util.Map;

public class Bettings {

    // TODO: key는 String(PlayerName)이 좋을까? Player가 좋을까?
    private final Map<String, Integer> bettings;

    public Bettings() {
        this.bettings = new HashMap<>();
    }

    public void put(String playerName, Integer bettingAmount) {
        bettings.put(playerName, bettingAmount);
    }
    
}