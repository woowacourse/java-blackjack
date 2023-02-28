package blackjackgame.domain;

public class Name {
    private static final int MAX_NAME_LENGTH = 15;
    private static final String ERROR_NAME_BLANK_MSG = "플레이어의 이름은 공백일 수 없습니다.";
    private static final String ERROR_NAME_LENGTH_OVER_MSG = "플레이어의 이름은 " + MAX_NAME_LENGTH + "글자를 초과할 수 없습니다.";

    private final String name;

    public Name(final String name) {
        validateBlank(name);
        String trimmedName = name.trim();
        validateRange(trimmedName);
        this.name = trimmedName;
    }

    private void validateBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_NAME_BLANK_MSG);
        }
    }

    private void validateRange(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ERROR_NAME_LENGTH_OVER_MSG);
        }
    }

}
