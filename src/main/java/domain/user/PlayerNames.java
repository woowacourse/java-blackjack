package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PlayerNames {
    private final List<Name> playerNames;

    public PlayerNames(List<Name> playerNames) {
        validateDuplicatedName(playerNames);
        this.playerNames = playerNames;
    }

    public Stream<Name> stream() {
        return playerNames.stream();
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
