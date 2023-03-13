package model.user;

public class Name {

    private final String name;

    public Name(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
