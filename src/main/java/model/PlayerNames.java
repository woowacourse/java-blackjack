package model;

import exception.GameException;
import java.util.HashSet;
import java.util.List;

public class PlayerNames {

    private final List<PlayerName> playerNames;

    public PlayerNames(List<PlayerName> playerNames) {
        validate(playerNames);
        this.playerNames = List.copyOf(playerNames);
    }

    private void validate(List<PlayerName> playerNames) {
        if (new HashSet<>(playerNames).size() != playerNames.size()) {
            throw new GameException("중복된 이름이 있습니다.");
        }
    }

    public List<PlayerName> getPlayerNames() {
        return List.copyOf(playerNames);
    }
}
