package blackjack.domain.gamer;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private static final int MINIMUM_NUMBER = 2;
    private static final int MAXIMUM_NUMBER = 8;
    private static final String NUMBER_OF_PLAYERS_ERROR_MESSAGE = "2명에서 8명 까지만 참가 가능합니다.";
    private static final String PLAYER_NAME_DUPLICATE_ERROR_MESSAGE = "참가자 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(final List<String> names) {
        validate(names);
        this.players = toPlayer(names);
    }

    private void validate(final List<String> names) {
        validateNumberOfPlayers(names);
        validateDuplicate(names);
    }

    private void validateNumberOfPlayers(final List<String> names) {
        int number = names.size();

        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(NUMBER_OF_PLAYERS_ERROR_MESSAGE);
        }
    }

    private void validateDuplicate(final List<String> names) {
        Set<String> duplicateNames = new HashSet<>(names);

        if (names.size() != duplicateNames.size()) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATE_ERROR_MESSAGE);
        }
    }

    private List<Player> toPlayer(final List<String> names) {
        return names.stream()
            .map(Player::new)
            .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
