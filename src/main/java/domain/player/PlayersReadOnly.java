package domain.player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersReadOnly {
    private final Players players;

    private PlayersReadOnly(Players players) {
        this.players = players;
    }

    public static PlayersReadOnly from(Players players) {
        return new PlayersReadOnly(players);
    }

    public List<String> getAllNames() {
        return players.getAllPlayerNames();
    }

    public PlayerReadOnly getDealer() {
        return PlayerReadOnly.from(players.getDealer());
    }

    public List<PlayerReadOnly> getGamblers() {
        return players.getGamblers()
                .stream().map(PlayerReadOnly::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<PlayerReadOnly> getAllPlayers() {
        List<PlayerReadOnly> players = new ArrayList<>();
        players.add(getDealer());
        players.addAll(getGamblers());
        return players;
    }
}
