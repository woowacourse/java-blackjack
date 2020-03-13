package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayers(playerNames);
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        this.players = Collections.unmodifiableList(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getName());
        }
        return Collections.unmodifiableList(playerNames);
    }

    private void validatePlayers(List<String> playerNames) {
        if (playerNames.size() > 7) {
            throw new IllegalArgumentException("최대 참여 가능 플레이어 수는 7 입니다.");
        }
    }
}
