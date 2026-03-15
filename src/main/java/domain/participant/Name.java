package domain.participant;

public record Name(String name) {
    public static final int NAME_LENGTH_THRESHOLD = 5;
    public static final String ERROR_NAME_EMPTY = "[ERROR] 이름은 비어있을 수 없습니다.";
    public static final String ERROR_NAME_LENGTH = "[ERROR] 이름은 5자를 초과할 수 없습니다.";

    public Name {
        validateNameLength(name);
    }

    private void validateNameLength(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ERROR_NAME_EMPTY);
        }

        if (name.length() > NAME_LENGTH_THRESHOLD) {
            throw new IllegalArgumentException(ERROR_NAME_LENGTH);
        }
    }
}
