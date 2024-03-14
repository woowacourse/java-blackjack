package domain.participant;

import constants.ErrorCode;
import exception.DuplicatePlayerNameException;
import exception.InvalidPlayersSizeException;
import java.util.List;
import java.util.Set;

public class Names {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 8;

    private final List<Name> playerNames;

    private Names(final List<Name> playerNames) {
        this.playerNames = playerNames;
    }

    public static Names from(final List<String> names) {
        List<Name> result = names.stream()
                .map(String::trim)
                .map(Name::new)
                .toList();
        validate(result);
        return new Names(result);
    }

    private static void validate(final List<Name> names) {
        validateSize(names);
        validateDuplicate(names);
    }

    private static void validateSize(final List<Name> names) {
        if (names.size() < MIN_SIZE || MAX_SIZE < names.size()) {
            throw new InvalidPlayersSizeException(ErrorCode.INVALID_SIZE);
        }
    }

    private static void validateDuplicate(final List<Name> names) {
        if (names.size() != Set.copyOf(names).size()) {
            throw new DuplicatePlayerNameException(ErrorCode.DUPLICATE_NAME);
        }
    }

    public List<Name> getPlayerNames() {
        return playerNames;
    }
}
