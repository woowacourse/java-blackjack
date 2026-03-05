package blackjack.domain;

public class Name {
    private static final int MAX_LENGTH = 10;
    private static final String NAME_PATTERN = "^[a-zA-Z가-힣]+(\\s[a-zA-Z가-힣]+)*$";

    private final String name;

    private Name(String name) {
        validate(name);
        this.name = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }

    private void validate(String name) {
        validateBlank(name);
        validateNameLength(name);
        validateKoreanOrEnglishOnly(name);
    }

    private void validateBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 1글자 이상이어야 합니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름의 길이는 10이 넘을 수 없습니다.");
        }
    }

    private void validateKoreanOrEnglishOnly(String name) {
        if (isInvalidFormat(name)) {
            throw new IllegalArgumentException("이름은 한글과 영어만 가능합니다.");
        }
    }

    private boolean isInvalidFormat(String name) {
        return !name.matches(NAME_PATTERN);
    }
}
