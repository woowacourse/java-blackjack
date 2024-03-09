package blackjack.domain.participant;

import blackjack.exception.InvalidNameLengthException;
import blackjack.exception.NonAlphabeticNameException;

import java.util.regex.Pattern;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣]*$");

    private final String value;

    public Name(String value) {
        validateNameLength(value);
        validateIsAlphabetic(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new InvalidNameLengthException();
        }
    }

    private void validateIsAlphabetic(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new NonAlphabeticNameException();
        }
    }
}
