package domain;

public class Name {
    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("플레이어 이름은 비어 있을 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }
}