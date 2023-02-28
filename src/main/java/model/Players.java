package model;

import java.util.List;
import java.util.stream.Collectors;

import static model.Player.DEADLER;

public class Players {

    private final List<Player> players;

    public Players(final List<String> playersName) {
        this.players = createPlayers(playersName);
    }

    public List<Player> getPlayers() {
        return players;
    }

    private List<Player> createPlayers(final List<String> playersName) {
        final List<Player> players = playersName.stream()
                .map(Player::new)
                .collect(Collectors.toList());

        players.add(0, DEADLER);

        return players;
    }
}
