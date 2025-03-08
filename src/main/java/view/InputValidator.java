package view;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern NAMES_PATTERN = Pattern.compile("([a-zA-Z]+)(,[a-zA-Z]+)*");

    private InputValidator() {}

    public static void validateNameFormat(String input) {
        if (!NAMES_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] (,)로 구분된 한글자 이상의 영어 이름을 입력해주세요.");
        }
    }
}
