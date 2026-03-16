package domain.participant;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {
    private static final String PLAYER_NAME_DUPLICATED_ERROR_MESSAGE = "[ERROR] 동명이인은 존재하지 않습니다!";

    private final List<Player> players;

    public Players(List<Player> players) {
        validateUniquePlayerName(players);
        this.players = players;
    }

    public List<Player> getAllPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validateUniquePlayerName(List<Player> players) {
        Set<Player> uniquePlayers = new HashSet<>();
        uniquePlayers.addAll(players);

        if (uniquePlayers.size() != players.size()) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATED_ERROR_MESSAGE);
        }
    }
}
