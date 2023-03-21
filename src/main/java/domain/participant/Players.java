package domain.participant;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {

    private static final int MAX_PLAYER_NUMBER = 5;
    protected static final String MAX_PLAYER_NUMBER_MSG = "플레이어 최대 인원수는 5명입니다.";
    protected static final String NO_PLAYER_MSG = "플레이어가 존재하지 않습니다.";

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

    public Player find(String playerName) {
        return players.stream()
            .filter(player -> player.getName().equals(playerName))
            .findAny()
            .orElseThrow(() -> new IllegalAccessError(NO_PLAYER_MSG));
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
