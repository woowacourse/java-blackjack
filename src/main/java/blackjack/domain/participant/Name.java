package blackjack.domain.participant;

import java.util.regex.Pattern;

public class Name {

    private static final Pattern NAME_FORMAT = Pattern.compile("[a-zA-Z]+");
    private static final String NAME_FORMAT_ERROR_MESSAGE = "사람 이름은 영문자만 가능합니다.";

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (isNotEnglish(value)) {
            throw new IllegalArgumentException(NAME_FORMAT_ERROR_MESSAGE);
        }
    }

    private boolean isNotEnglish(final String value) {
        return !NAME_FORMAT.matcher(value).matches();
    }

    public String getValue() {
        return this.value;
    }
}