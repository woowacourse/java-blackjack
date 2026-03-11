package domain.vo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static void validateName(String name) {
        validateKorEng(name);
    }

    private static void validateKorEng(String name) {
        Pattern namePattern = Pattern.compile("^[a-zA-Z가-힣]+$");

        if(!namePattern.matcher(name).matches()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 영어와 한글만 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
