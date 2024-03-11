package domain.participant;

import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class Players {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validateSize(players);
        validateNotDuplicate(players);
        this.players = List.copyOf(players);
    }

    private void validateSize(final List<Player> players) {
        if (players.isEmpty() || players.size() > MAX_SIZE) {
            throw new IllegalArgumentException("플레이어의 수는 최소 %d명 최대 %d명입니다 : 현재 %d명"
                    .formatted(MIN_SIZE, MAX_SIZE, players.size()));
        }
    }

    private void validateNotDuplicate(final List<Player> players) {
        int nonDuplicatedSize = new HashSet<>(players).size();
        if (nonDuplicatedSize != players.size()) {
            throw new IllegalArgumentException("중복된 이름을 가진 플레이어는 존재할 수 없습니다.");
        }
    }

    public void forEach(final Consumer<Player> action) {
        players.forEach(action);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
