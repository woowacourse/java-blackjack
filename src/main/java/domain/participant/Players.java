package domain.participant;

import java.util.List;
import java.util.stream.Stream;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(final List<Player> players) {
        if (isNotSatisfiedSize(players)) {
            throw new IllegalArgumentException("플레이어의 수는 최소 1명, 최대 4명입니다.");
        }

        if (hasDuplicateName(players)) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private boolean isNotSatisfiedSize(List<Player> players) {
        return players.size() < 1 || 4 < players.size();
    }

    private boolean hasDuplicateName(List<Player> players) {
        return players.stream()
                .map(Participant::getName)
                .distinct()
                .count() != players.size();
    }

    public Stream<Player> stream() {
        return players.stream();
    }
}
