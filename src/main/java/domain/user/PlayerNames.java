package domain.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerNames {
    private static final int MAX_AMOUNT = 8;
    private static final int MIN_AMOUNT = 2;
    private final List<Name> playerNames;

    public PlayerNames(List<Name> playerNames) {
        validateNamesAmount(playerNames);
        validateDuplicatedName(playerNames);
        this.playerNames = playerNames;
    }

    public Stream<Name> stream() {
        return playerNames.stream();
    }

    private void validateNamesAmount(List<Name> playerNames) {
        if (playerNames.size() > MAX_AMOUNT || playerNames.size() < MIN_AMOUNT) {
            throw new IllegalArgumentException(
                    "플레이어의 수는 %d명에서 %d명이여야 합니다: %d".formatted(MIN_AMOUNT, MAX_AMOUNT, playerNames.size()));
        }
    }

    private void validateDuplicatedName(List<Name> names) {
        Set<Name> distinctNames = new HashSet<>(names);
        List<Name> duplicatedNames = new ArrayList<>(names);
        distinctNames.forEach(duplicatedNames::remove);
        throwExceptionIfNotEmpty(duplicatedNames);
    }

    private void throwExceptionIfNotEmpty(List<Name> duplicatedNames) {
        if (!duplicatedNames.isEmpty()) {
            throw new IllegalArgumentException("중복된 이름은 입력할 수 없습니다: %s".formatted(
                    duplicatedNames.stream()
                            .map(Name::value)
                            .collect(Collectors.joining(", "))));
        }
    }
}
