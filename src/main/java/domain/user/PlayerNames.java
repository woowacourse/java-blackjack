package domain.user;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static domain.util.NullValidator.validateNull;

public class PlayerNames {
    private final List<String> names;

    public PlayerNames(List<String> names) {
        validate(names);
        this.names = trimNames(names);
    }

    private void validate(List<String> names) {
        validateNull(names);
        validateEmptyValue(names);
        names = trimNames(names);
        validateEmptyName(names);
        validateDuplication(names);
    }

    private void validateEmptyValue(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("빈 값이 입력되었습니다.");
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
