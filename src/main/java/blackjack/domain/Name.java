package blackjack.domain;

import java.util.regex.Pattern;

public class Name {

    private static final Pattern pattern = Pattern.compile("([A-Z][a-z]+)");

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
        if (!pattern.matcher(value).matches()) {
            throw new IllegalArgumentException("이름은 영문이어야 합니다.");
        }
    }
}
