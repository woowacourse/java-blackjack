package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Players {
    public static final int MAX_PLAYER_NUMBER = 7;
    public static final String EMPTY_OR_NULL_EXCEPTION_MESSAGE = "Empty or null player names exception.";
    public static final String INVALID_INPUT_SIZE_EXCEPTION_MESSAGE = "Input player over 7 exception.";

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayers(playerNames);
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        this.players = Collections.unmodifiableList(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getName());
        }
        return Collections.unmodifiableList(playerNames);
    }

    private void validatePlayers(List<String> playerNames) {
        if (Objects.isNull(playerNames) || playerNames.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_OR_NULL_EXCEPTION_MESSAGE);
        }
        if (playerNames.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException(INVALID_INPUT_SIZE_EXCEPTION_MESSAGE);
        }
    }
}
