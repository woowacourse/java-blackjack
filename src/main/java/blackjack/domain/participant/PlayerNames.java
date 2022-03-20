package blackjack.domain.participant;

import blackjack.domain.Name;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerNames {

    private static final int PLAYER_COUNT_LOWER_BOUND = 2;
    private static final int PLAYER_COUNT_UPPER_BOUND = 8;

    private final List<Name> names;

    public PlayerNames(final List<String> names) {
        validate(names);
        this.names = names.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private void validate(final List<String> names) {
        validateDuplicate(names);
        validatePlayerCount(names);
    }

    private void validateDuplicate(final List<String> names) {
        final long distinctNameCount = names.stream()
                .distinct()
                .count();

        if (distinctNameCount != names.size()) {
            throw new IllegalArgumentException("이름은 중복을 허용하지 않습니다.");
        }
    }

    private void validatePlayerCount(final List<String> names) {
        if (names.size() < PLAYER_COUNT_LOWER_BOUND) {
            throw new IllegalArgumentException(PLAYER_COUNT_LOWER_BOUND + "명 이상의 참가자가 필요합니다.");
        }
        if (names.size() > PLAYER_COUNT_UPPER_BOUND) {
            throw new IllegalArgumentException(PLAYER_COUNT_UPPER_BOUND + "명 까지만 참여할 수 있습니다.");
        }
    }

    public List<Name> getNames() {
        return names;
    }
}
