package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final int MIN_PLAYERS_SIZE = 1;
    private static final int MAX_PLAYERS_SIZE = 4;

    private final List<Player> players;

    private Players(List<Player> players) {
        validatePlayersSize(players);
        validatePlayersDuplicated(players);
        this.players = players;
    }

    public static Players from(List<Name> playerNames) {
        return playerNames.stream()
                .map(Player::createInitialStatePlayer)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    private void validatePlayersSize(List<Player> players) {
        if (players.size() < MIN_PLAYERS_SIZE || MAX_PLAYERS_SIZE < players.size()) {
            throw new IllegalArgumentException(
                    String.format("플레이어는 %d명 이상 %d명 이하만 참여 가능합니다. 입력 인원수: %d", MIN_PLAYERS_SIZE, MAX_PLAYERS_SIZE,
                            players.size()));
        }
    }

    private void validatePlayersDuplicated(List<Player> players) {
        if (players.stream().distinct().count() != players.size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }
}
