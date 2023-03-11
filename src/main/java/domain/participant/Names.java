package domain.participant;

import view.ExceptionMessage;

import java.util.List;

public class Names {
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final int MAXIMUM_PLAYER_COUNT = 7;
    private final List<Name> names;

    public Names(List<Name> names) {
        validate(names);
        this.names = names;
    }

    private void validate(List<Name> names) {
        validateNumberOfNames(names);
        validateNoDuplication(names);
    }

    private void validateNumberOfNames(List<Name> names) {
        if (names.size() < MINIMUM_PLAYER_COUNT || names.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER_OF_PLAYER.getMessage());
        }
    }

    private void validateNoDuplication(List<Name> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException(ExceptionMessage.NAME_IS_DUPLICATED.getMessage());
        }
    }

    public List<Name> getNames() {
        return names;
    }
}
