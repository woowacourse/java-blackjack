package domain.participant;

import java.util.List;

public class PlayerRoster {

    private final List<Name> playerNames;

    public PlayerRoster(List<Name> playerNames) {
        validate(playerNames);
        this.playerNames = playerNames;
    }

    private void validate(List<Name> playerNames) {
        validateNotNull(playerNames);
        validateNotDuplicate(playerNames);
    }

    private void validateNotNull(List<Name> playerNames) {
        if (playerNames == null || playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름 목록은 이름들을 가져야 합니다.");
        }
    }

    private void validateNotDuplicate(List<Name> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    public List<Name> getPlayerNames() {
        return List.copyOf(playerNames);
    }
}
