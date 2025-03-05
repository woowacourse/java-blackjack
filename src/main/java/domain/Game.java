package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    public static final int MAX_PLAYER_COUNT = 5;
    private final Map<Player, GameResult> players = new HashMap<>();

    public Game(List<Player> players) {
        validatePlayerCount(players);
        players.forEach(player -> this.players.put(player, GameResult.NONE));
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참여자는 최대 5명입니다.");
        }
    }
}
