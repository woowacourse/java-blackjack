package domain.player;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Players {
    private static final int MAX_PLAYER_NUMBER = 8;
    private final List<Player> value;

    public Players(final List<Player> value) {
        validate(value);
        this.value = value;
    }

    public static Players fromNames(final List<String> names) {
        return new Players(names.stream()
                .map(name -> new Player(new Name(name)))
                .collect(Collectors.toList()));
    }

    private void validate(final List<Player> players) {
        validatePlayerNumbers(players);
        validateDuplicate(players);
    }

    private void validatePlayerNumbers(final List<Player> players) {
        if (isInvalidPlayersNumber(players)) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private boolean isInvalidPlayersNumber(final List<Player> players) {
        return players.size() > MAX_PLAYER_NUMBER;
    }

    private void validateDuplicate(final List<Player> players) {
        if (hasDuplicatePlayers(players)) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다");
        }
    }

    private boolean hasDuplicatePlayers(final List<Player> players) {
        return Set.copyOf(players).size() != players.size();
    }

    public Player findPlayerByName(final String name) {
        return this.value.stream()
                .filter(r -> r.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("플레이어가 존재하지 않습니다."));
    }

    public Stream<Player> stream() {
        return this.value.stream();
    }

    public List<Player> getValue() {
        return Collections.unmodifiableList(value);
    }

}
