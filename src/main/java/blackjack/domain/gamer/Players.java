package blackjack.domain.gamer;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Players {

    static final String ERROR_MIN_PLAYER_COUNT = "플레이어 수는 1명 이상이어야 합니다.";
    private static final int MIN_PLAYER_COUNT = 1;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validateMinPlayerCount(players);
        this.players = players;
    }

    private static void validateMinPlayerCount(List<Player> players) {
        if (players.size() < MIN_PLAYER_COUNT) {
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
