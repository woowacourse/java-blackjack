package blackjack.domain.participant;

import blackjack.domain.result.PlayerResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private static final String NULL_ERR_MSG = "플레이어의 이름이 없습니다.";
    private static final String MAX_PLAYER_ERR_MSG = "플레이어는 최대 %d명입니다.";
    private static final int MAX_PLAYER = 5;

    private final List<Player> players;

    public Players(List<String> names) {
        Objects.requireNonNull(names, NULL_ERR_MSG);

        if (names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(String.format(MAX_PLAYER_ERR_MSG, MAX_PLAYER));
        }

        players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<PlayerResult> createPlayerResults(Dealer dealer) {
        return players.stream()
                .map(player -> player.createPlayerResult(dealer))
                .collect(Collectors.toList());
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
