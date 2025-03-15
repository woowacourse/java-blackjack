package domain.participant;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 플레이어가 없습니다."));
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
