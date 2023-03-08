package blackjack.domain.user;

import blackjack.domain.game.GameResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private static final int MAX_PLAYER_COUNT = 10;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayers(players);
        this.players = players;
    }

    private void validatePlayers(final List<Player> players) {
        validatePlayerCount(players);
        validateDuplicatedNames(players);
    }

    private void validatePlayerCount(final List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어들은 " + MAX_PLAYER_COUNT + "명 이상이어야합니다.");
        }

        if (players.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("플레이어들은 10명 이하여야 합니다.");
        }
    }

    private void validateDuplicatedNames(final List<Player> players) {
        final long count = players.stream()
                .map(Player::getName)
                .distinct()
                .count();

        if (count != players.size()) {
            throw new IllegalArgumentException("플레이어들의 이름은 고유하여야 합니다.");
        }
    }

    public Map<String, GameResult> toResults(Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> {
                            Score score = player.getScore();
                            return dealer.declareGameResult(score.getValue());
                        },
                        (a, b) -> a, HashMap::new));
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
