package blackjack.domain.participant;

import java.util.List;

public record PlayerNames(List<PlayerName> names) {

    public PlayerNames {
        validateEntryNotEmpty(names);
        validateEachPlayerNameUnique(names);
    }

    private void validateEntryNotEmpty(List<PlayerName> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어가 없습니다");
        }
    }

    private void validateEachPlayerNameUnique(List<PlayerName> names) {
        if (countUniquePlayer(names) != names.size()) {
            throw new IllegalArgumentException("[ERROR] 중복되는 플레이어의 이름이 존재합니다");
        }
    }

    private int countUniquePlayer(List<PlayerName> names) {
        return (int) names.stream()
                .distinct()
                .count();
    }

    public static PlayerNames create(List<String> rawNames) {
        List<PlayerName> names = rawNames.stream()
                .map(PlayerName::new)
                .toList();

        return new PlayerNames(names);
    }
}
