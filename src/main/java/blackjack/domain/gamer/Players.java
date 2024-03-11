package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Players {

    static final String ERROR_DUPLICATED_NAME = "플레이어의 이름은 중복될 수 없습니다.";
    static final String ERROR_MIN_PLAYER_COUNT = "플레이어 수는 1명 이상이어야 합니다.";
    private static final int MIN_PLAYER_COUNT = 1;

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> names) {
        final List<Player> players = new ArrayList<>();
        validateDuplicatedName(names);
        validateMinPlayerCount(names);
        for (final String name : names) {
            players.add(new Player(new Name(name)));
        }
        return new Players(players);
    }

    private static void validateDuplicatedName(final List<String> names) {
        if (names.size() != Set.copyOf(names).size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NAME);
        }
    }

    private static void validateMinPlayerCount(List<String> names) {
        if (names.size() < MIN_PLAYER_COUNT) {
            throw new IllegalArgumentException(ERROR_MIN_PLAYER_COUNT);
        }
    }

    public Player findByName(final Name name) {
        return players.stream()
                .filter(player -> Objects.equals(player.getName(), name))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("해당 이름을 가진 플레이어가 존재하지 않습니다."));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
