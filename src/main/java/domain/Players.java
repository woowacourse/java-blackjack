package domain;

import java.util.Iterator;
import java.util.List;

public class Players implements Iterable<Player> {
    private static final int MAX_PLAYER_NUMBER = 5;

    private final List<Player> players;

    private Players(List<Player> players) {
        validatePlayersCount(players.size());
        this.players = List.copyOf(players);
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public static void validatePlayersCount(int playerCount) {
        if (playerCount > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException("플레이 가능한 최대 인원을 초과했습니다.");
        }
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
