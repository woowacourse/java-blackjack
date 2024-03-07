package domain;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class PlayerNames {
    private final List<PlayerName> playerNames;

    private PlayerNames(List<PlayerName> playerNames) {
        validateDuplicated(playerNames);
        this.playerNames = playerNames;
    }

    public static PlayerNames of(List<String> playerNames) {
        return playerNames.stream()
                .map(PlayerName::new)
                .collect(collectingAndThen(toList(), PlayerNames::new));
    }

    private void validateDuplicated(List<PlayerName> playerNames) {
        if (Set.of(playerNames).size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름에 중복이 존재합니다.");
        }
    }
}
