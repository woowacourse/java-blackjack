package model;

import exception.GameException;
import java.util.HashSet;
import java.util.List;

public class Players {

    private static final String DUPLICATED_NAME = "중복된 이름이 있습니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        if (new HashSet<>(players).size() != players.size()) {
            throw new GameException(DUPLICATED_NAME);
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
