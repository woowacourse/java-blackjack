package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    private static final int MINIMUM_NAMES_SIZE = 1;
    private static final int MAXIMUM_NAMES_SIZE = 10;

    static final String NAME_DUPLICATE_MESSAGE = "중복된 이름은 허용하지 않습니다.";
    static final String NAMES_SIZE_INVALID_MESSAGE
            = String.format("참가자는 %d명 이상 %d명 이하 입니다.", MINIMUM_NAMES_SIZE, MAXIMUM_NAMES_SIZE);
    static final String NAMES_SIZE_BETS_SIZE_NOT_EQUAL_MESSAGE = "사용자 이름의 수와 베팅 금액의 수는 동일해야 합니다.";

    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names, List<Integer> bets) {
        validateDuplicate(names);
        validatePlayerNamesSize(names);
        validatePlayerSizeBetSizeEqual(names, bets);

        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), bets.get(i)));
        }
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

    private void validatePlayerSizeBetSizeEqual(List<String> names, List<Integer> bets) {
        if (names.size() != bets.size()) {
            throw new IllegalArgumentException(NAMES_SIZE_BETS_SIZE_NOT_EQUAL_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
