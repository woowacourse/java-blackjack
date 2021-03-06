package blackjack.domain.user;

import java.util.regex.Pattern;

public class Name {
    private static final Pattern PATTERN = Pattern.compile("^[가-힣a-zA-Z]*$");
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(String.format("이름을 잘못 입력하였습니다. (입력값 : %s)", name));
        }
    }

    String getName() {
        return name;
    }
}
