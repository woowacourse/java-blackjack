package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Players implements Iterable<Player> {
    private static final int MAX_PLAYER_NUMBER = 5;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames) {
        validatePlayersCount(playerNames);
        List<Player> players = createPlayers(playerNames);
        return new Players(players);
    }

    private static void validatePlayersCount(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 플레이 가능한 최대 인원을 초과했습니다.");
        }
    }

    private static List<Player> createPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = new Player(playerName);
            players.add(player);
        }
        return players;
    }

    @Override
    public Iterator<Player> iterator() {
        return Collections.unmodifiableList(players).iterator();
    }
}
