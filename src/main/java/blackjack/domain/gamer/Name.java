package blackjack.domain.gamer;

import blackjack.domain.utils.RegexUtil;
import java.util.Objects;

public class Name {
    private static final String EXCEPTION_BE_ENGLISH_OR_KOREAN = "영문 또는 한글로 이루어진 이름이어야 합니다.";
    private final String name;

    public Name(String name) {
        this.name = validateWord(name.trim());
    }

    private String validateWord(String nameValue) {
        if (RegexUtil.isAlphaOrKorean(nameValue)) {
            return nameValue;
        }
        throw new IllegalArgumentException(EXCEPTION_BE_ENGLISH_OR_KOREAN);
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
