package domain;

public class Name {
    private final String value;


    public Name(String value) {
        validateBlank(value);
        this.value = value;
    }

    private void validateBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
