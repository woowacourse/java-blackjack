package blackjack.model.participant;

import java.util.Collection;
import java.util.List;

public record UniqueNames(
        List<Name> names
) {
    public UniqueNames {
        validateDuplicatedNames(names);
    }

    public static UniqueNames from(Collection<Name> names) {
        return new UniqueNames(List.copyOf(names));
    }

    private void validateDuplicatedNames(Collection<Name> playerNames) {
        long uniqueNameCount = playerNames.stream()
                .distinct()
                .count();

        if (uniqueNameCount != playerNames.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }
}
