package domain.player.attribute;

import static util.Constants.MAXIMUM_NAME_LENGTH;
import static util.Constants.MINIMUM_NAME_LENGTH;

public class Name {
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEqualName(String name) {
        return this.name.equals(name);
    }

    private void validate(String name) {
        validateNameIsEnglish(name);
        validateNameRangeTwoToTen(name);
    }

    private void validateNameIsEnglish(String name) {
        for (char ch : name.toCharArray()) {
            if (!Character.isAlphabetic(ch)) {
                throw new IllegalArgumentException("이름은 모두 영어로 입력되어야 합니다");
            }
        }
    }

    private void validateNameRangeTwoToTen(String name) {
        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 2글자 이상 10글자 이하로 입력되어야 합니다");
        }
    }
}
