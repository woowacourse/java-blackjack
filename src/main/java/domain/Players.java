package domain;

import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players createByNames(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(playerName -> new Player(playerName, Cards.createEmpty()))
                .toList();
        return new Players(players);
    }

    public static Players create(List<Player> players) {
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<String> getAllPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
