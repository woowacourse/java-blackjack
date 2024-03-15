package domain;

import java.util.Collections;
import java.util.List;

public class Players {

    static final String NAME_DUPLICATE_MESSAGE = "중복된 이름은 허용하지 않습니다.";
    private static final int MINIMUM_NAMES_SIZE = 1;
    private static final int MAXIMUM_NAMES_SIZE = 10;
    static final String NAMES_SIZE_INVALID_MESSAGE
            = String.format("참가자는 %d명 이상 %d명 이하 입니다.", MINIMUM_NAMES_SIZE, MAXIMUM_NAMES_SIZE);

    private final List<Player> players;

    public Players(List<String> names) {
        validateDuplicate(names);
        validatePlayerNamesSize(names);

        this.players = names.stream()
                .map(Player::new)
                .toList();
    }

    private void validateDuplicate(List<String> names) {
        boolean isDuplicated = names.stream().distinct().count() != names.size();

        if (isDuplicated) {
            throw new IllegalArgumentException(NAME_DUPLICATE_MESSAGE);
        }
    }

    private void validatePlayerNamesSize(List<String> names) {
        boolean isValidateLength =
                MINIMUM_NAMES_SIZE <= names.size() && names.size() <= MAXIMUM_NAMES_SIZE;

        if (!isValidateLength) {
            throw new IllegalArgumentException(NAMES_SIZE_INVALID_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
