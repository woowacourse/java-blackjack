package blackjack.domain.participant;

public class Name {
    public static final int MINIMUM_NAME_LENGTH = 1;
    private static final String NAME_INPUT_ERROR_MESSAGE = "이름은 " + MINIMUM_NAME_LENGTH + "자 이상이어야 합니다.";

    private final String name;

    public Name(String value) {
        String trimmedName = value.trim();
        validateName(trimmedName);
        this.name = trimmedName;
    }

    private void validateName(String name) {
        if (name.length() < MINIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(NAME_INPUT_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return this.name;
    }
}
