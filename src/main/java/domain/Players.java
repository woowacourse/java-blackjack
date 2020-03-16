package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Players {
    public static final int MAX_PLAYER_NUMBER = 7;
    public static final String INVALID_INPUT_SIZE_EXCEPTION_MESSAGE = "Input player over 7 exception.";

    private final List<Player> players;

    public Players(Names playerNames) {
        validatePlayers(playerNames.getNames());
        List<Player> players = new ArrayList<>();
        for (Name playerName : playerNames.getNames()) {
            players.add(new Player(playerName));
        }
        this.players = Collections.unmodifiableList(players);
    }

    private void validatePlayers(List<Name> playerNames) {
        if (playerNames.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException(INVALID_INPUT_SIZE_EXCEPTION_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Name> getPlayerNames() {
        List<Name> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getName());
        }
        return Collections.unmodifiableList(playerNames);
    }
}
