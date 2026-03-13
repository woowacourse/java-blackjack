package domain.game;

import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Participant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GamblersGameResult {

    private Map<String, GameResult> gamblersResult;

    public GamblersGameResult(Participant dealer, Gamblers gamblers) {
        gamblersResult = new HashMap<>();
        for(Participant gambler : gamblers.getGamblers()) {
            GameResult result = GameResult.determine(dealer, gambler);
            gamblersResult.put(gambler.getName(), result);
        }
    }

    public GameResult getMatchResult(String name) {
        return gamblersResult.get(name);
    }
}
