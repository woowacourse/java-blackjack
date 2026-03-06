package domain.player;

public class Name {
    private static final String ERROR_EMPTY = "이름은 공백일 수 없습니다.";
    private static final String ERROR_LENGTH = "이름은 10글자 이내여야 합니다.";
    private final String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name from(String rawName) {
        validate(rawName);

        return new Name(rawName);
    }

    private static void validate(String rawName) {
        if (rawName == null || rawName.isBlank()) {
            throw new IllegalArgumentException(ERROR_EMPTY);
        }

        if (rawName.length() > 10) {
            throw new IllegalArgumentException(ERROR_LENGTH);
        }

    }
}
