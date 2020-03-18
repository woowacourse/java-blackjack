package domain.user;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerNames {
    private final List<String> names;

    public PlayerNames(List<String> names) {
        validate(names);
        this.names = names;
    }

    private void validate(List<String> names) {
        validateNull(names);
        names = trimNames(names);
        validateEmptyName(names);
        validateDuplication(names);
    }

    private void validateNull(List<String> names) {
        if (Objects.isNull(names) || names.isEmpty()) {
            throw new IllegalArgumentException("null 혹은 빈 값이 입력되었습니다.");
        }
    }

    private List<String> trimNames(List<String> names) {
        return names.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private void validateEmptyName(List<String> names) {
        boolean hasEmptyName = names.stream()
                .anyMatch(String::isEmpty);
        if (hasEmptyName) {
            throw new IllegalArgumentException("공백으로 이루어진 이름은 사용할 수 없습니다.");
        }
    }

    private void validateDuplication(List<String> names) {
        Set<String> playerNames = new HashSet<>(names);
        if (playerNames.size() != names.size()) {
            throw new IllegalArgumentException("중복되는 이름이 존재합니다.");
        }
    }

    public List<String> get() {
        return Collections.unmodifiableList(names);
    }
}
