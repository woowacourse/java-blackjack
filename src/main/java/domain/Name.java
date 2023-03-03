package domain;

public class Name {
    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백이나 빈 값이 들어갈 수 없습니다.");
        }
    }

    public String getValue() {
        return this.value;
    }
}
