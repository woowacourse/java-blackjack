package domain.vo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final String ALPHABET_AND_KOREAN_TEXT_REG = "^[a-zA-Z가-힣]+$";
    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    private static void validateName(String name) {
        validateKorEng(name);
        validateNotDealer(name);
    }

    private static void validateKorEng(String name) {
        Pattern namePattern = Pattern.compile(ALPHABET_AND_KOREAN_TEXT_REG);

        if(!namePattern.matcher(name).matches()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 영어와 한글만 가능하므로, "+ name + "은/는 불가능합니다.");
        }
    }

    private static void validateNotDealer(String name){
        if (name.equals("딜러")) {
            throw new IllegalArgumentException("[ERROR] 딜러는 플레이어 이름으로 사용할 수 없습니다.");
        }
    }

    public String getValueOf() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Name other = (Name) obj;
        return Objects.equals(this.name, other.name);
    }
}
