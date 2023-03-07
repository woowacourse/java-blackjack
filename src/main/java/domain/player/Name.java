package domain.player;

public class Name {

    private final String value;

    private Name(final String name) {
        validateEmpty(name);
        this.value = name;
    }

    public static Name of(final String name) {
        return new Name(name);
    }

    private void validateEmpty(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈칸일 수 없습니다.");
        }
    }

    public String value() {
        return value;
    }
}
