package domain.participant;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    public static final int MAX_SIZE = 5;

    private final Set<Player> players;

    public Players(List<Player> players) {
        validateDuplicate(players);
        validateSize(players);
        this.players = new HashSet<>(players);
    }

    private void validateDuplicate(List<Player> players) {
        if (players.stream().distinct().count() != players.size()) {
            throw new IllegalArgumentException("중복된 플레이어가 있습니다.");
        }
    }

    private void validateSize(List<Player> players) {
        if (players.size() > MAX_SIZE) {
            throw new IllegalArgumentException("플레이어는 최대 5명입니다.");
        }
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }
}
