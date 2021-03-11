package blackjack.domain.participant;

public class Name {
    private static final String NAME_INPUT_ERROR_MESSAGE = "이름은 1자 이상이어야 합니다.";

    private final String name;

    public Name(String value) {
        String trimmedName = value.trim();
        validateName(trimmedName);
        this.name = trimmedName;
    }

    private void validateName(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException(NAME_INPUT_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return this.name;
    }
}
