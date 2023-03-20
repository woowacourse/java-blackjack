package domain.participant;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Players implements Iterable<Player> {

    private static final int MAX_PLAYER_NUMBER = 5;
    protected static final String MAX_PLAYER_NUMBER_MSG = "플레이어 최대 인원수는 5명입니다.";

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames) {
        validatePlayerNumber(playerNames);
        List<Player> players = playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validatePlayerNumber(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException(MAX_PLAYER_NUMBER_MSG);
        }
    }

    public Stream<Player> stream() {
        return players.stream();
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
