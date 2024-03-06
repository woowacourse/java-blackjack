package domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerHandsDto {

    private final Map<String, List<String>> playerHands;

    public PlayerHandsDto(final Map<String, List<String>> playerHands) {
        this.playerHands = playerHands;
    }

    public static PlayerHandsDto from(final Players players) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        for (Player player : players.getNames()) {
            result.put(player.getName(), player.getCards());
        }
        return new PlayerHandsDto(result);
    }

    public Map<String, List<String>> getPlayerHands() {
        return playerHands;
    }
}
