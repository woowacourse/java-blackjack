package blackjack.domain.participant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private static final int NUMBER_OF_MINIMUM_PLAYER = 1;
    private static final int NUMBER_OF_MAXIMUM_PLAYER = 7;

    private final List<Player> players;

    public static Players createPlayers(final String input) {
        final List<Player> players = Arrays.stream(input.split(","))
                .map(Player::new)
                .collect(Collectors.toList());
        validate(players);
        return new Players(players);
    }

    private Players(final List<Player> players) {
        this.players = players;
    }

    private static void validate(final List<Player> players) {
        validateNumberOfPlayers(players);
        validateDuplicatedNames(players);
    }

    private static void validateNumberOfPlayers(final List<Player> names) {
        if (names.size() < NUMBER_OF_MINIMUM_PLAYER || names.size() > NUMBER_OF_MAXIMUM_PLAYER) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상 7명 이하만 가능합나다.");
        }
    }

    private static void validateDuplicatedNames(final List<Player> names) {
        final Set<String> namesWithoutDuplicate = names.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());
        if (namesWithoutDuplicate.size() != names.size()) {
            throw new IllegalArgumentException("각 플레이어는 중복된 이름을 가질 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}
