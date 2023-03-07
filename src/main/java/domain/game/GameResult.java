package domain.game;

import domain.user.Dealer;
import domain.user.Name;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<String, Boolean> playerResults = new HashMap<>();
    private final Map<Boolean, Integer> dealerResult = new HashMap<>();

    public GameResult(List<Name> playerNames) {
        playerNames.forEach(name -> playerResults.put(name.getName(), false));
        dealerResult.put(true, 0);
        dealerResult.put(false, 0);
    }
}
