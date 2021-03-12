package blackjack.domain.gamer;

import blackjack.domain.utils.RegexUtil;
import java.util.Objects;

public class Name {
    private final String name;

    public Name(String name) {
        this.name = validateWord(name.trim());
    }

    private String validateWord(String nameValue) {
        if (RegexUtil.isAlphaOrKorean(nameValue)) {
            return nameValue;
        }
        throw new IllegalArgumentException();
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
