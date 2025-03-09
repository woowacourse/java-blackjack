package view;

import static view.InputView.UserResponses;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern INPUT_PATTERN = Pattern.compile("^[가-힣a-zA-Z ]+(?:,[가-힣a-zA-Z ]+)*$");

    public static void validateInputFormat(String names) {
        if (!INPUT_PATTERN.matcher(names).matches()) {
            throw new IllegalArgumentException("한글, 영문, 공백, 쉼표만 입력가능합니다.");
        }
    }

    public static void validateDuplicate(List<String> splittedNames) {
        if (splittedNames.size() != new HashSet<>(splittedNames).size()) {
            throw new IllegalArgumentException("입력은 중복될 수 없습니다.");
        }
    }

    public static void validateUserResponse(String userResponse) {
        if (!UserResponses.containsKey(userResponse)) {
            throw new IllegalArgumentException("응답은 반드시 y 혹은 n만 가능합니다.");
        }
    }
}
