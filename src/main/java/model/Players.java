package model;

import constant.PlayerErrorCode;
import exception.GameException;
import java.util.HashSet;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        if (new HashSet<>(players).size() != players.size()) {
            throw new GameException(PlayerErrorCode.DUPLICATED_NAME);
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
