package model;

import constant.ErrorMessage;
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
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_NAME.getMessage());
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}