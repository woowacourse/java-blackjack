package domain.user;

public record Name(String value) {
    private static final int MAX_NAME_SIZE = 5;

    public Name {
        validate(value);
    }

    private void validate(String value) {
        validateNameSize(value);
        validateBlankName(value);
    }

    private void validateBlankName(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("공백인 이름이 존재합니다.");
        }
    }

    private void validateNameSize(String value) {
        if (value.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException("이름은 최대 5자입니다: %s".formatted(value));
        }
    }
}
