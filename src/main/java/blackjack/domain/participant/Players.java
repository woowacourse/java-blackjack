package blackjack.domain.participant;

import blackjack.domain.result.PlayerResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static blackjack.domain.participant.Name.EMPTY_NAME_ERR_MSG;
import static blackjack.domain.participant.Name.NULL_NAME_ERR_MSG;

public class Players {
    private static final int MAX_PLAYER = 5;
    static final String MAX_PLAYER_ERR_MSG = String.format("플레이어는 최대 %d명입니다.", MAX_PLAYER);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }
}
