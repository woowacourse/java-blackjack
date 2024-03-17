package domain;

public record Name(String name) {
    static final int MIN_NAME_LENGTH = 1;
    static final int MAX_NAME_LENGTH = 5;
    static final String NAME_LENGTH_MESSAGE
            = String.format("이름은 %d에서 %d글자 사이여야 합니다", MIN_NAME_LENGTH, MAX_NAME_LENGTH);

    public Name {
        validateLength(name);
    }

    private void validateLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || MAX_NAME_LENGTH < name.length()) {
            throw new IllegalArgumentException(NAME_LENGTH_MESSAGE);
        }
    }
}
