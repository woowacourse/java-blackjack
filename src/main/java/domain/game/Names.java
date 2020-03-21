package domain.game;

import java.util.*;

public class Names {
    public static final String ERROR_VALIDATE_MESSAGE = "입력이 잘못되었습니다.";

    private final List<String> names;

    public Names(List<String> names) {
        validate(names);
        this.names = names;
    }

    private void validate(List<String> names) {
        validateNull(names);
        validateEmptyName(names);
        validateDuplication(names);
    }

    private void validateNull(List<String> names) {
        if (Objects.isNull(names) || names.isEmpty()) {
            throw new IllegalArgumentException(ERROR_VALIDATE_MESSAGE);
        }
    }

    private void validateEmptyName(List<String> names) {
        boolean hasEmptyName = names.stream()
                .map(String::isEmpty)
                .findAny()
                .orElse(false);
        if (hasEmptyName) {
            throw new IllegalArgumentException(ERROR_VALIDATE_MESSAGE);
        }
    }

    private void validateDuplication(List<String> names) {
        Set<String> playerNames = new HashSet<>(names);
        if (playerNames.size() != names.size()) {
            throw new IllegalArgumentException(ERROR_VALIDATE_MESSAGE);
        }
    }

    public List<String> get() {
        return Collections.unmodifiableList(names);
    }
}
