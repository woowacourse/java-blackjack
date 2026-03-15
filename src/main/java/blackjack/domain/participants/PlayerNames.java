package blackjack.domain.participants;

import java.util.Arrays;
import java.util.List;

public record PlayerNames(List<Name> names) {
    private static final String DELIMITER = ",";
    private static final int INCLUDE_EMPTY_ELEMENT = -1;

    public static PlayerNames from(String rawPlayerNames) {
        List<Name> playerNames = Arrays.stream(
                rawPlayerNames.split(DELIMITER, INCLUDE_EMPTY_ELEMENT))
            .map(Name::new)
            .toList();
        validateDuplicatedNames(playerNames);
        return new PlayerNames(playerNames);
    }

    private static void validateDuplicatedNames(List<Name> playerNames) {
        if (containsDealerName(playerNames)) {
            throw new IllegalArgumentException("플레이어의 이름은 딜러의 이름과 동일할 수 없습니다.");
        }
        if (isDuplicated(playerNames)) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private static boolean containsDealerName(List<Name> playerNames) {
        return playerNames.stream()
            .anyMatch(Dealer::isDealerName);
    }

    private static boolean isDuplicated(List<Name> playerNames) {
        return countDistinct(playerNames) != playerNames.size();
    }

    private static long countDistinct(List<Name> playerNames) {
        return playerNames.stream()
            .distinct()
            .count();
    }

    @Override
    public List<Name> names() {
        return List.copyOf(names);
    }
}
