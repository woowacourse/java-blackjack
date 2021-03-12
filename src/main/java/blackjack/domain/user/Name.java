package blackjack.domain.user;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final Pattern PATTERN_ENGLISH = Pattern.compile("^[a-zA-Z]*$");
    private static final Pattern PATTERN_KOREAN = Pattern.compile("^[가-힣]*$");
    private static final String EMPTY = "";

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (isInvalidCharacter(name) || isEmpty(name)) {
            throw new IllegalArgumentException("유효하지 않은 이름입니다.");
        }
    }

    private boolean isInvalidCharacter(String name) {
        return !PATTERN_ENGLISH.matcher(name).matches() && !PATTERN_KOREAN.matcher(name).matches();
    }

    private boolean isEmpty(String name) {
        return EMPTY.equals(name);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
