package blackjack.domain.participant;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;
    private static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-Z가-힣]*$");

    private final String name;

    public Name(String name) {
        validateNameLength(name);
        validateName(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.isBlank() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 %d글자 이하만 가능합니다.", MAX_NAME_LENGTH));
        }
    }

    private void validateName(String name) {
        if (isInvalidName(name)) {
            throw new IllegalArgumentException(String.format("이름은 한글 또는 영어만 가능합니다. 입력값: %s", name));
        }
    }

    private boolean isInvalidName(String name) {
        return !NAME_REGEX.matcher(name).matches();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name name1)) {
            return false;
        }
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
