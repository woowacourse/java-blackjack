package domain;

import java.util.List;

import static domain.Name.DEALER_NAME_MESSAGE;

public record PlayerNames(List<String> names) {

    static final String NAME_DUPLICATE_MESSAGE = "중복된 이름은 허용하지 않습니다.";
    static final String NAMES_SIZE_INVALID_MESSAGE = "참가자는 1명 이상 10명 이하 입니다.";
    private static final int MINIMUM_NAMES_SIZE = 1;
    private static final int MAXIMUM_NAMES_SIZE = 10;

    public PlayerNames {
        validateNotDealerName(names);
        validateDuplicate(names);
        validatePlayerNamesSize(names);
    }

    private void validateNotDealerName(List<String> names) {
        if (names.contains("딜러")) {
            throw new IllegalArgumentException(DEALER_NAME_MESSAGE);
        }
    }

    private void validateDuplicate(List<String> names) {
        boolean isDuplicated = names.stream().distinct().count() != names.size();

        if (isDuplicated) {
            throw new IllegalArgumentException(NAME_DUPLICATE_MESSAGE);
        }
    }

    private void validatePlayerNamesSize(List<String> names) {
        boolean isValidateLength =
                MINIMUM_NAMES_SIZE <= names.size() && names.size() <= MAXIMUM_NAMES_SIZE;

        if (!isValidateLength) {
            throw new IllegalArgumentException(NAMES_SIZE_INVALID_MESSAGE);
        }
    }
}
