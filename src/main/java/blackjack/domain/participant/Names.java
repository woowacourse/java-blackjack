package blackjack.domain.participant;

import java.util.List;
import java.util.stream.Collectors;

public class Names {

    private static final int MAX_PLAYER_COUNT = 5;
    private static final int MIN_PLAYER_COUNT = 1;
    private static final String OVER_RANGE_MESSAGE =
            "사용자 수는 " + MIN_PLAYER_COUNT + " 이상 " + MAX_PLAYER_COUNT + " 이하여야 합니다. 현재 : %s 명입니다";

    private final List<Name> names;

    public Names(final List<String> names) {
        validatePlayerNames(names);
        this.names = List.copyOf(names.stream()
                .map(Name::new)
                .collect(Collectors.toList()));
    }

    private static void validatePlayerNames(final List<String> playerNames) {
        validateNull(playerNames);
        validatePlayerCount(playerNames);
        validateDuplicate(playerNames);
    }

    private static void validateNull(final List<String> playerNames) {
        if (playerNames == null) {
            throw new IllegalArgumentException("사용자 이름이 입력되지 않았습니다");
        }
    }

    private static void validatePlayerCount(final List<String> playerNames) {
        if (MIN_PLAYER_COUNT > playerNames.size() || playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(String.format(OVER_RANGE_MESSAGE, playerNames.size()));
        }
    }

    private static void validateDuplicate(final List<String> playerNames) {
        if (playerNames.stream().distinct().count() != playerNames.size()) {
            throw new IllegalArgumentException("사용자의 이름이 중복됩니다.");
        }
    }

    List<Name> getNames() {
        return names;
    }
}
