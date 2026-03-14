package domain;

import java.util.regex.Pattern;

public record Name(String name) {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+$");

    public Name {
        validateKoreanAndEnglish(name);
    }

    private static void validateKoreanAndEnglish(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("[ERROR] 이름은 영어 또는 한국어만 가능합니다.");
        }
    }
}
