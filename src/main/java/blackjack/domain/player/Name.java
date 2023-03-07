package blackjack.domain.player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Name {
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 10;
    private static final Pattern INPUT_NAMES_PATTERN = Pattern.compile("([a-zA-Z]{1,10})(,[a-zA-Z]{1,10})*");

    private final String name;

    public Name(String name) {
        validateLength(name);
        validateContinuousComma(name);
        this.name = name;
    }

    private void validateLength(String name) {
        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("이름의 길이는 %d 이상 %d 이하로 입력해주세요.", MINIMUM_NAME_LENGTH, MAXIMUM_NAME_LENGTH));
        }
    }

    private void validateContinuousComma(String names) {
        Matcher matcher = INPUT_NAMES_PATTERN.matcher(names);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("입력된 플레이어들의 이름 형식이 올바르지 않습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
