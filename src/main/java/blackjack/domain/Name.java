package blackjack.domain;

public class Name {

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        validateEmpty(value);
        validateLength(value);
    }

    private void validateEmpty(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("공백은 허용되지 않습니다.");
        }
    }

    private void validateLength(String value) {
        if (value.length() > 100) {
            throw new IllegalArgumentException("길이는 100자를 초과할 수 없습니다.");
        }
    }
    
    public String getValue() {
        return value;
    }
}
