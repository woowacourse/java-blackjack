package domain.participant;

import java.util.List;
import java.util.stream.Stream;

public class Players {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 4;

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

    public Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다."));
    }

    public Stream<Player> stream() {
        return players.stream();
    }
}
