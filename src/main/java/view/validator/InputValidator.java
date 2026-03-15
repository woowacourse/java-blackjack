package view.validator;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class InputValidator {

    private static final int MAX_NAMES_SIZE = 5;
    private static final String NUMBER_REGEX = "^\\d+$";

    public static void validateDuplicate(List<String> names) {
        Set<String> uniqueNames = new HashSet<>(names);

        if (uniqueNames.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다. 다시 입력해주세요.");
        }
    }

    public static void validateSize(List<String> names) {
        if (names.size() > MAX_NAMES_SIZE)
            throw new IllegalArgumentException(String.format("인원 수는 %d명 이하여야 합니다. 다시 입력해주세요.", MAX_NAMES_SIZE));
    }

    public static int validateNumber(String userInput) {
        if (!Pattern.matches(NUMBER_REGEX, userInput)) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다. 다시 입력해주세요.");
        }

        return validateMaxInteger(userInput);
    }

    private static int validateMaxInteger(String userInput) {
        BigInteger value = new BigInteger(userInput);

        if (value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new IllegalArgumentException("int 범위를 초과했습니다. 다시 입력해주세요.");
        }

        return value.intValue();
    }
}
