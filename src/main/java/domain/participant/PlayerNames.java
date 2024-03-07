package domain.participant;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public record PlayerNames(List<PlayerName> values) {

    public PlayerNames {
        validateDuplicated(values);
    }

    public static PlayerNames of(List<String> playerNames) {
        return playerNames.stream()
                .map(PlayerName::new)
                .collect(collectingAndThen(toList(), PlayerNames::new));
    }

    private void validateDuplicated(List<PlayerName> playerNames) {
        Set<PlayerName> playerNamesSet = Set.copyOf(playerNames);
        if (playerNamesSet.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름에 중복이 존재합니다.");
        }
    }
}
