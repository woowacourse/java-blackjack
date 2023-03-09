package domain.participant;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public void initDistribute() {
        for (Player player : players) {
            player.takeCard(2);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
