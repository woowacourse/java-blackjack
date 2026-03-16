package domain.participant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class Players {
    private static final int MAX_PLAYER = 8;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayersNumber(players);
        this.players = new ArrayList<>(players);
    }

    public void forEach(Consumer<Player> action) {
        players.forEach(action);
    }

    public static void validatePlayersNumber(List<Player> players) {
        validateMinimumPlayers(players);
        validateMaximumPlayers(players);
        validateDuplicateName(players);
    }

    private static void validateMaximumPlayers(List<Player> players) {
        if (players.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private static void validateMinimumPlayers(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상이어야 합니다.");
        }
    }

    private static void validateDuplicateName(List<Player> players) {
        Set<Name> uniqueNames = new HashSet<>();
        for (Player player : players) {
            uniqueNames.add(player.getName());
        }
        if (players.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("중복된 참가자 이름이 있습니다!");
        }
    }
}
