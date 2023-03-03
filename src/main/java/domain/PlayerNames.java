package domain;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerNames {
    private static final int MAX_SIZE = 8;
    public static final String INVALID_PLAYER_SIZE = "참여자 수는 1명 이상 8명 이하여야 합니다.";
    public static final String PLAYER_NAME_DUPLICATION = "중복된 이름을 허용할 수 없습니다.";

    private final List<PlayerName> names;

    private PlayerNames(List<PlayerName> names) {
        validate(names);
        this.names = names;
    }

    public static PlayerNames from(List<String> names) {
        List<PlayerName> playerNames = names.stream()
                .map(PlayerName::new)
                .collect(Collectors.toList());

        return new PlayerNames(playerNames);
    }

    private void validate(List<PlayerName> names) {
        validateSize(names);
        validateDuplication(names);
    }

    private void validateSize(List<PlayerName> names) {
        if(names.isEmpty() || names.size() > MAX_SIZE){
            throw new IllegalArgumentException(INVALID_PLAYER_SIZE);
        }
    }

    private void validateDuplication(List<PlayerName> names) {
        long distinctionSize = names.stream()
                .map(PlayerName::getName)
                .distinct()
                .count();

        if (names.size() != distinctionSize) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATION);
        }
    }
}
