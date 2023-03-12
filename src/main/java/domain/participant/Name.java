package domain.participant;

import java.util.regex.Pattern;

public final class Name {

    private static final Pattern NAME_REGEX = Pattern.compile("^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|\\s]*$");
    private static final int MAX_LENGTH_EXCLUSIVE = 10;

    private final String name;

    public Name(final String name) {
        validateLength(name);
        validateFormat(name);
        this.name = name;
    }

    private static void validateFormat(final String name) {
        if (!NAME_REGEX.matcher(name).matches()) {
            throw new IllegalArgumentException("이름에 숫자나 특수문자가 포함될 수 없습니다.");
        }
    }

    private static void validateLength(final String name) {
        if (name.length() > MAX_LENGTH_EXCLUSIVE) {
            throw new IllegalArgumentException("이름의 길이는 10글자 이하여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
