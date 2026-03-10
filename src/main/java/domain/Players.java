package domain;

import constant.PolicyConstant;
import exception.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<String> names) {
        validatePlayerNames(names);
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
        this.players = players;
    }

    private void validatePlayerNames(List<String> names) {
        validatePlayerDuplication(names);
        validatePlayerCount(names.size());
    }

    private void validatePlayerDuplication(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_DUPLICATED.getMessage());
        }
    }

    private void validatePlayerCount(int playerCount) {
        if (!(PolicyConstant.PLAYER_MIN_COUNT <= playerCount
                && playerCount <= PolicyConstant.PLAYER_MAX_COUNT)) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player getPlayer(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
