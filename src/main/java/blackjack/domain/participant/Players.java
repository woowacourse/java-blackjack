package blackjack.domain.participant;

import blackjack.domain.result.PlayerResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private static final int MAX_PLAYER = 5;
    private static final String MAX_PLAYER_ERR_MSG = String.format("플레이어는 최대 %d명입니다.", MAX_PLAYER);
    private static final String NULL_NAME_ERR_MSG = "플레이어의 이름이 없습니다.";
    private static final String EMPTY_NAME_ERR_MSG = "플레이어의 이름에 빈 값이 올 수 없습니다.";

    private final List<Player> players;

    public Players(List<String> names) {
        validateNames(names);

        this.players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void validateNames(List<String> names) {
        Objects.requireNonNull(names, NULL_NAME_ERR_MSG);

        if (names.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_ERR_MSG);
        }

        if (names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(MAX_PLAYER_ERR_MSG);
        }
    }

    public List<PlayerResult> createPlayerResults(Dealer dealer) {
        return players.stream()
                .map(player -> player.createPlayerResult(dealer))
                .collect(Collectors.toList());
    }

    public List<String> names() {
        return players.stream()
                .map(Player::name)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
