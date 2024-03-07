package player;

public class Name {

    private final String name;

    Name(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        validateNonBlankName(name);
        validateNameLength(name);
    }

    private void validateNameLength(String name) {
        if (name.length() < 2 || name.length() > 5) {
            throw new IllegalArgumentException("[ERROR] 이름은 2글자 이상 5글자 이하여야 합니다.");
        }
    }

    private void validateNonBlankName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백일 수 없습니다.");
        }
    }

    String getName() {
        return name;
    }
}
