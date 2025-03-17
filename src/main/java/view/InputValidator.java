package view;

import java.math.BigInteger;
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
        if (!rawBettingMoney.chars().allMatch(Character::isDigit)) {
            throw new NumberFormatException("숫자가 아닙니다.");
        }
    }

    public static void validateIntegerRange(String rawBettingMoney) {
        BigInteger bigInteger = new BigInteger(rawBettingMoney);
        if (isOutOfIntRange(bigInteger)) {
            throw new IllegalArgumentException("허용된 숫자 범위가 아닙니다.");
        }
    }

    private static boolean isOutOfIntRange(BigInteger bigInteger) {
        return bigInteger.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 ||
                bigInteger.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0;
    }
}
