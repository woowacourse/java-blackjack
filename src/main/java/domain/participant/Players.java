package domain.participant;

import java.util.List;
import java.util.stream.Stream;

public class Players {

    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 4;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(final List<Player> players) {
        if (players.size() < MIN_SIZE || MAX_SIZE < players.size()) {
            throw new IllegalArgumentException(String.format("플레이어의 수는 최소 %s명, 최대 %s명입니다.", MIN_SIZE, MAX_SIZE));
        }

        if (hasDuplicateName(players)) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
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
