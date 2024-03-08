package domain.participant;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public record PlayerNames(List<PlayerName> values) {

    public PlayerNames {
        validateSize(values);
        validateDuplicated(values);
    }

    public static PlayerNames of(final List<String> playerNames) {
        return playerNames.stream()
                .map(PlayerName::new)
                .collect(collectingAndThen(toList(), PlayerNames::new));
    }

    private void validateDuplicated(final List<PlayerName> playerNames) {
        Set<PlayerName> playerNamesSet = Set.copyOf(playerNames);
        if (playerNamesSet.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름에 중복이 존재합니다.");
        }
    }

    private void validateSize(final List<PlayerName> playerNames) {
        if (playerNames.size() > 10) {
            throw new IllegalArgumentException(String.format("%d는 올바른 참여 인원수가 아닙니다. 게임에 참여할 사람은 10명 이하여야 합니다.", playerNames.size()));
        }
    }
}
