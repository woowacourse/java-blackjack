package util;

import java.util.regex.Pattern;

import static util.BlackJackConstant.MAX_NAME_LENGTH;

public class Validator {

    private static final String STRING_REGEX = "^[a-zA-Z]*$";

    public static void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH)  {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }
    }

    public static void validateNameEng(String name) {
        if (!Pattern.matches(STRING_REGEX, name)) {
            throw new IllegalArgumentException("플레이어 이름은 영문자만 포함되어야 합니다.");
        }
    }

    public static int validateMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }
        return money;
    }

    private Validator() {}
}
