package player;

public class Name {

    private static final int MAX_NAME_LENGTH = 10;

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNameLength(name);
        validateNotBlankName(name);
    }

    private void validateNameLength(String name) {
        if (10 < name.length()) {
            throw new IllegalArgumentException("이름은 " + MAX_NAME_LENGTH + "글자를 넘을 수 없습니다.");
        }
    }

    private void validateNotBlankName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈값이 될 수 없습니다.");
        }
    }
}
