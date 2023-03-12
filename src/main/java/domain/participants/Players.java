package domain.participants;

import static java.util.stream.Collectors.toList;

import domain.message.ExceptionMessage;
import java.util.List;
import java.util.stream.Stream;

public class Players {

    private static final int MIN_BETTING_ACCOUNT = 1000;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(final List<Player> players) {
        validateMinimumAccount(players);

    }

    private void validateMinimumAccount(final List<Player> players) {
        boolean isValidate = players.stream()
                .anyMatch(player -> player.getAccount() < MIN_BETTING_ACCOUNT);

        if (isValidate) {
            throw new IllegalArgumentException(ExceptionMessage.BETTING_MONEY_NEED_MORE.getMessage());
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
