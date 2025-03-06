package domain;

import java.util.List;

public class Players {

    public static final int MAX_PLAYER_COUNT = 5;
    
    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayerCount(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 최소 플레이어 수는 1명입니다.");
        }
        if (playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 최대 플레이어 수는 " + MAX_PLAYER_COUNT + "명입니다.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
