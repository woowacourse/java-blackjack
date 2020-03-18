package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private static final int MAX_PLAYER_NUMBER = 7;
    private static final String INVALID_INPUT_SIZE_EXCEPTION_MESSAGE = "Input player over 7 exception.";

    private final List<Player> players;

    public Players(Names playerNames) {
        validatePlayers(playerNames.getNames());

        List<Player> players = new ArrayList<>();

        for (Name playerName : playerNames.getNames()) {
            Player player = new Player(playerName);
            players.add(player);
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

    public List<Integer> getAllScore() {
        return players.stream()
                .map(User::getScore)
                .collect(Collectors.toList());
    }
}
