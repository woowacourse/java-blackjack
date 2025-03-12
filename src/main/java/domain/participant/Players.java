package domain.participant;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Players {

    public static final int MAX_SIZE = 5;

    private final List<Player> players;

    public Players(List<Player> players) {
        validateDuplicate(players);
        validateSize(players);
        this.players = players;
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

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(players);
    }
}
