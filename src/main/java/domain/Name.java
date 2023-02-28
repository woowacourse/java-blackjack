package domain;

public class Name {
    private static final String INVALID_NAME_LENGTH_MESSAGE = "이름길이는 5자를 초과 할 수 없습니다.";
    private static final String INVALID_NAME_BLANK_MESSAGE = "이름은 공백일 수 없습니다.";
    private static final int MAX_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validateNameBlank(name);
        validNameLength(name);
        this.name = name;
    }

    private void validateNameBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(INVALID_NAME_BLANK_MESSAGE);
        }
    }

    private void validNameLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }

}
