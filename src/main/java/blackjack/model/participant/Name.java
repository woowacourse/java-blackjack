package blackjack.model.participant;

public record Name(
        String value
) {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 5;

    public Name {
        validateName(value);
    }

    private void validateName(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("이름은 비어 있을 수 없으며, 공백만 포함할 수 없습니다.");
        }
        if (value.length() < MIN_NAME_LENGTH || MAX_NAME_LENGTH < value.length()) {
            throw new IllegalArgumentException("이름은 %d자 이상 %d자 이하로만 가능합니다. 입력: %s"
                    .formatted(MIN_NAME_LENGTH, MAX_NAME_LENGTH, value));
        }
    }
}
