package domain.participant;

public class Name {

    private final String value;

    public Name(final String name) {
        validateEmpty(name);
        this.value = name;
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
