package util;

import java.util.List;
import java.util.Set;
import model.participant.exception.DuplicatePlayerNameException;

public final class PlayerNamesValidator {
    private PlayerNamesValidator() {
    }

    public static void validate(List<String> names) {
        validateDuplicateNames(names);
    }

    private static void validateDuplicateNames(List<String> names) {
        if (Set.copyOf(names).size() != names.size()) {
            throw new DuplicatePlayerNameException(names);
        }
    }
}
