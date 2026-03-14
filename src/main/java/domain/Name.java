package domain;

public class Name {
    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈 값이 될 수 없습니다.");
        }
    }
}
