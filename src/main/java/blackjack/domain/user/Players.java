package blackjack.domain.user;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {

    private static final String INVALID_PLAYERS_COUNT_ERROR_MESSAGE = "플레이어 수는 1명 이상이어야 합니다.";

    private final List<Player> players;

    public Players(List<String> playersName) {
        this.players = makePlayers(playersName);
        validatePlayersNumber(players);
    }

    private List<Player> makePlayers(List<String> inputPlayers) {
        return inputPlayers.stream()
            .map(Player::new)
            .collect(Collectors.toList());
    }

    private void validatePlayersNumber(List<Player> players) {
        if (players.size() < 1) {
            throw new IllegalArgumentException(INVALID_PLAYERS_COUNT_ERROR_MESSAGE);
        }
    }

    public String getPlayersName() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.joining(", "));
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }
}
