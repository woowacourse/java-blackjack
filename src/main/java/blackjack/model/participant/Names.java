package blackjack.model.participant;

import java.util.Collection;
import java.util.List;

public record Names(
        Collection<Name> names
) {
    public Names(Collection<Name> names) {
        validateDuplicatedNames(names);

        this.names = List.copyOf(names);
    }

    public Collection<Name> get() {
        return List.copyOf(names);
    }

    private void validateDuplicatedNames(Collection<Name> playerNames) {
        long uniqueNameCount = playerNames.stream()
                .distinct()
                .count();

        if (uniqueNameCount != playerNames.size()) {
            throw new IllegalArgumentException("참가자의 이름은 중복될 수 없습니다.");
        }
    }
}
