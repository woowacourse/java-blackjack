package domain.user;

public class Name {
    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름이 공백입니다.");
        }
    }

    public String getValue() {
        return name;
    }
}
