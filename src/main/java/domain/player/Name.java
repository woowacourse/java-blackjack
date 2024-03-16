package domain.player;

public record Name(String value) {
    private static final int MAX_LENGTH = 10;

    public Name {
        validate(value);
    }

    private void validate(final String name) {
        validateEmptiness(name);
        validateLength(name);
    }

    private void validateLength(final String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 %d자 이하로 입력해주세요", MAX_LENGTH));
        }
    }

    private void validateEmptiness(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
    }
}
