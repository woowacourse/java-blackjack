package domain.participant;

import exception.ExceptionMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        validateUnique(players);
        this.players = new ArrayList<>(players);
    }

    private void validateUnique(List<Player> players) {
        long distinctCount = players.stream()
                .map(Player::getName)
                .distinct()
                .count();

        if (distinctCount != players.size()) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_PARTICIPANT_NAME.getMessage());
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
