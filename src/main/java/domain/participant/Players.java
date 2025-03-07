package domain.participant;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    private final Set<Player> players;

    public Players(List<Player> players) {
        validateDuplicate(players);
        this.players = new HashSet<>(players);
    }

    private void validateDuplicate(List<Player> players) {
        if (players.stream().distinct().count() != players.size()) {
            throw new IllegalArgumentException("중복된 플레이어가 있습니다.");
        }
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }
}
