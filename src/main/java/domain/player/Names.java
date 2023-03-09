package domain.player;

import java.util.List;
import java.util.stream.Collectors;

public class Names {
    private static final int MAX_SIZE = 8;
    private static final String INVALID_PLAYER_SIZE = "참여자 수는 1명 이상 8명 이하여야 합니다.";
    private static final String PLAYER_NAME_DUPLICATION = "중복된 이름을 허용할 수 없습니다.";

    private final List<Name> names;

    private Names(List<Name> names) {
        validate(names);
        this.names = names;
    }

    public static Names from(List<String> names) {
        List<Name> playerNames = names.stream()
                .map(Name::from)
                .collect(Collectors.toList());

        return new Names(playerNames);
    }

    private void validate(List<Name> names) {
        validateSize(names);
        validateDuplication(names);
    }

    private void validateSize(List<Name> names) {
        if (names.isEmpty() || names.size() > MAX_SIZE) {
            throw new IllegalArgumentException(INVALID_PLAYER_SIZE);
        }
    }

    private void validateDuplication(List<Name> names) {
        long distinctionSize = names.stream()
                .map(Name::getName)
                .distinct()
                .count();

        if (names.size() != distinctionSize) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATION);
        }
    }

    public List<Name> getNames() {
        return names;
    }
}
