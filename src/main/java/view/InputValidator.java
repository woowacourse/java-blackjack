package view;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern INPUT_PATTERN = Pattern.compile("^[^,]+(?:,[^,]+)*$");

    public static void validateInputFormat(String names) {
        if (!INPUT_PATTERN.matcher(names).matches()) {
            throw new IllegalArgumentException("쉼표로 구분되지 않았습니다.");
        }
    }

    public static void validateDuplicate(List<String> splittedNames) {
        if (splittedNames.size() != new HashSet<>(splittedNames).size()) {
            throw new IllegalArgumentException("입력은 중복될 수 없습니다.");
        }
    }

    public static void validateInputMoney(String rawBettingMoney) {
        try {
            Integer.parseInt(rawBettingMoney);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("정수가 아닙니다.");
        }
    }
}
