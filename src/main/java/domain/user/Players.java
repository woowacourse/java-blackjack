package domain.user;

import domain.name.Names;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final int PLAYER_MIN_SIZE = 1;
    private static final int PLAYER_MAX_SIZE = 4;
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<Player> players) {
        return new Players(players);
    }

    public static Players of(final Names names) {
        validate(names);
        List<Player> players = names.getNames().stream()
                .map(name -> new Player(name))
                .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validate(final Names names) {
        validateSize(names);
    }

    private static void validateSize(final Names names) {
        if (names.size() < PLAYER_MIN_SIZE || names.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException(
                    String.format("플레이어 수는 %d명 이상, %d명 이하여야 합니다.", PLAYER_MIN_SIZE, PLAYER_MAX_SIZE));
        }
    }

    public List<String> getPlayerNames() {
        return players.stream().map(player -> player.getName()).collect(Collectors.toList());
    }

    public Player get(int index) {
        return players.get(index);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
