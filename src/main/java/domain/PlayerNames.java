package domain;

import java.util.List;

public record PlayerNames(List<String> names) {

    static final String NAME_DUPLICATE_MESSAGE = "중복된 이름은 허용하지 않습니다.";

    public PlayerNames {
        validateDuplicate();
    }

    private void validateDuplicate() {
        boolean isDuplicated = names.stream().distinct().count() != names.size();

        if (isDuplicated) {
            throw new IllegalArgumentException(NAME_DUPLICATE_MESSAGE);
        }
    }
}
