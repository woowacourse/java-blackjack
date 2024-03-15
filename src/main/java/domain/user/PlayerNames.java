package domain.user;

import java.util.ArrayList;
import java.util.List;
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
        List<Name> distinctNames = new ArrayList<>();
        for (Name name : names) {
            throwExceptionIfContains(distinctNames, name);
            distinctNames.add(name);
        }
    }

    private void throwExceptionIfContains(List<Name> distinctNames, Name name) {
        if (distinctNames.contains(name)) {
            throw new IllegalArgumentException(
                    "중복된 이름은 입력할 수 없습니다: %s".formatted(name.value()));
        }
    }
}
