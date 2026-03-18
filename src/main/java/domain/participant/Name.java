package domain.participant;

public record Name(String name) {
    private static final int NAME_LENGTH_THRESHOLD = 5;

    public Name {
        validateNameLength(name);
    }

    private void validateNameLength(String name) {
        if (name.length() > NAME_LENGTH_THRESHOLD) {
            throw new IllegalArgumentException("이름은 5글자 이내여야 합니다.");
        }
    }
}
