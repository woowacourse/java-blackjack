package domain;

import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Players {

    private static final String DEALER_NAME = "딜러";
    private static final int MIN_PLAYERS_SIZE = 1;
    private static final int MAX_PLAYERS_NUMBER = 4;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(final List<Player> players) {
        List<String> playerNames = parserPlayerNames(players);

        validatePlayerNumbers(playerNames);
        validateNameDuplicated(playerNames);
        validateNameNotDealer(playerNames);
    }

    private void validatePlayerNumbers(List<String> playerNames) {
        if (playerNames.size() < MIN_PLAYERS_SIZE || MAX_PLAYERS_NUMBER < playerNames.size()) {
            throw new IllegalArgumentException("플레이어의 수는 최소 1명, 최대 4명입니다.");
        }
    }

    private void validateNameDuplicated(final List<String> playerNames) {
        Set<String> notDuplicatedPlayerNames = new HashSet<>(playerNames);

        if (playerNames.size() != notDuplicatedPlayerNames.size()) {
            throw new IllegalArgumentException("참여자의 이름은 중복이 되면 안됩니다.");
        }
    }

    private void validateNameNotDealer(final List<String> playerNames) {
        if (playerNames.contains(DEALER_NAME)) {
            throw new IllegalArgumentException("참여자의 이름은 '" + DEALER_NAME + "'가 되면 안됩니다.");
        }
    }


    private List<String> parserPlayerNames(final List<Player> players) {
        return players.stream()
                .map(player -> player.getName())
                .collect(toList());
    }

    public Stream<Player> stream() {
        return players.stream();
    }
}
