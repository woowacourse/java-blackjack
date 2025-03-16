package console;

import java.util.List;
import java.util.Set;
import model.participant.Name;

public record PlayerNames(List<Name> playerNames) {
    private static final int MAX_PLAYER_COUNT = 5;

    public PlayerNames {
        validateIsNotEmpty(playerNames);
        validatePlayerCount(playerNames);
        validateDuplicateName(playerNames);
    }

    private void validateIsNotEmpty(List<Name> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름이 입력되지 않았습니다.");
        }
    }

    private void validatePlayerCount(List<Name> playerNames) {
        if (playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참여자는 최대 5명입니다.");
        }
    }

    private void validateDuplicateName(List<Name> playerNames) {
        if (playerNames.size() != Set.copyOf(playerNames).size()) {
            throw new IllegalArgumentException("[ERROR] 이름은 중복될 수 없습니다.");
        }
    }
}
