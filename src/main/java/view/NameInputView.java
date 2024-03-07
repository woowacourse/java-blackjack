package view;

import java.util.Arrays;
import java.util.List;

public class NameInputView {
    private static final String SEPARATOR = ",";

    // TODO: 배열 대신 컬렉션 사용하기 (요구사항)
    public static List<String> getNames() {
        String input = Console.getInputFromConsole();
        validateSeparator(input);
        String[] splitInput = input.split(SEPARATOR);
        validateBlank(splitInput);
        return Arrays.stream(splitInput).toList();
    }

    private static void validateSeparator(String input) {
        if (input.startsWith(SEPARATOR) || input.endsWith(SEPARATOR) || input.contains(SEPARATOR + SEPARATOR)) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }
    }

    private static void validateBlank(String[] split) {
        boolean isInputContainsBlank = Arrays.stream(split).anyMatch(String::isBlank);
        if (isInputContainsBlank) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }
    }
}
