package blackjack.domain.participant;

import blackjack.domain.result.PlayersResults;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    public static final String NULL_ARGUMENT_ERR_MSG = "Null 인자로 플레이어를 생성할 수 없습니다.";
    public static final String EMPTY_ARGUMENT_ERR_MSG = "Empty 리스트 인자로 플레이어 인자를 생성할 수 없습니다.";
    public static final int MAX_PLAYER = 5;
    public static final String MAX_PLAYER_ERR_MSG = String.format("플레이어는 최대 %d명입니다.", MAX_PLAYER);

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayers(players);
        this.players = players;
    }

    private <T extends Participant> void validatePlayers(List<T> players) {
        Objects.requireNonNull(players, NULL_ARGUMENT_ERR_MSG);

        if (players.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ARGUMENT_ERR_MSG);
        }

        if (players.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(MAX_PLAYER_ERR_MSG);
        }
    }

    public PlayersResults createPlayerResults(Dealer dealer) {
        return this.players.stream()
                .map(player -> player.createPlayerResult(dealer))
                .collect(Collectors.collectingAndThen(Collectors.toList(), PlayersResults::new));
    }

    public List<String> names() {
        return this.players.stream()
                .map(Player::name)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
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
