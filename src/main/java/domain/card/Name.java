package domain.card;

import static exception.ErrorMessage.INVALID_NAME;

public record Name(String value) {

    private static final String NAME_REGEX = "^[a-zA-Z0-9가-힣]{2,8}$";

    public Name(String value) {
        String trimmed = value.trim();
        validateName(trimmed);
        this.value = trimmed;
    }

    private void validateName(String value) {
        if (!value.matches(NAME_REGEX)) {
            throw new IllegalArgumentException(INVALID_NAME.getMessage());
        }
    }
}
