package blackjack.domain.user;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private static final String MAX_PLAYER_ERR_MSG = "플레이어는 최대 5명입니다.";
    private static final String NULL_ERR_MSG = "플레이어의 이름이 없습니다.";
    public static final int MAX_PLAYER = 5;

    private final List<Player> players;

    public Players(List<String> names) {
        Objects.requireNonNull(names, NULL_ERR_MSG);

        if (names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(MAX_PLAYER_ERR_MSG);
        }

        players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(x -> x.getName().toString())
                .collect(Collectors.toList());
    }
}
