package model.participant;

import java.util.List;
import java.util.Set;
import model.participant.exception.DuplicatePlayerNameException;

public final class PlayerNames {
    private final List<String> values;

    private PlayerNames(List<String> values) {
        this.values = List.copyOf(values);
    }

    public static PlayerNames from(List<String> names) {
        validateDuplicateNames(names);

        return new PlayerNames(names);
    }

    private static void validateDuplicateNames(List<String> names) {
        if (Set.copyOf(names).size() != names.size()) {
            throw new DuplicatePlayerNameException(names);
        }
    }

    public List<String> asList() {
        return List.copyOf(values);
    }
}
