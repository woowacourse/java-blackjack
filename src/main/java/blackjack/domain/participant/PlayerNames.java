package blackjack.domain.participant;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerNames {

    private static final int PLAYER_MIN_SIZE = 2;
    private static final int PLAYER_MAX_SIZE = 8;

    private final List<Name> names;

    public PlayerNames(List<String> nameStrings) {
        nameStrings = List.copyOf(nameStrings);
        validate(nameStrings);
        names = createNames(nameStrings);
    }

    private void validate(List<String> nameStrings) {
        validateSize(nameStrings);
        validateNotDuplicated(nameStrings);
    }

    private void validateSize(List<String> nameStrings) {
        if (nameStrings.size() < PLAYER_MIN_SIZE || nameStrings.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 2~8인의 플레이어가 참가할 수 있습니다.");
        }
    }

    private void validateNotDuplicated(List<String> nameStrings) {
        if (nameStrings.stream().distinct().count() != nameStrings.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private List<Name> createNames(List<String> nameStrings) {
        return nameStrings.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public List<Name> getNames() {
        return names;
    }
}
