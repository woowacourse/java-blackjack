package blackjack.domain.gamer;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[가-힣ㄱ-ㅎㅏ-ㅣ|a-z|A-Z]+");
    private final String name;

    public Name(String name) {
        this.name = validateWord(name.trim());
    }

    private String validateWord(String nameValue) {
        if (!NAME_PATTERN.matcher(nameValue).matches()) {
            throw new IllegalArgumentException();
        }
        return nameValue;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
