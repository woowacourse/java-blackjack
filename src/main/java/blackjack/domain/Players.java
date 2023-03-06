package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<String> findAvailablePlayerNames() {
        List<String> availablePlayerNames = new ArrayList<>();
        for (Player player : players) {
            addAvailablePlayer(player, availablePlayerNames);
        }
        return availablePlayerNames;
    }

    private void addAvailablePlayer(Player player, List<String> availablePlayerNames) {
        if (player.isAvailable()) {
            availablePlayerNames.add(player.getName());
        }
    }

    public Player findPlayerBy(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름을 가진 플레이어를 찾을 수 없습니다."));
    }

    public List<Player> players() {
        return new ArrayList<>(players);
    }
}
