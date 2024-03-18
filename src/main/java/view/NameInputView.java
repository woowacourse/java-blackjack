package view;

import java.util.List;

public class NameInputView {
    private static final String SEPARATOR = ",";

    public static List<String> getNames() {
        Console.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = Console.getInputFromConsole();
        validateSeparator(input);
        List<String> splitInput = List.of(input.split(SEPARATOR));
        validateBlank(splitInput);
        return splitInput;
    }

    private static void validateSeparator(String input) {
        if (input.startsWith(SEPARATOR) || input.endsWith(SEPARATOR) || input.contains(SEPARATOR + SEPARATOR)) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }
    }

    private static void validateBlank(List<String> split) {
        boolean isInputContainsBlank = split.stream().anyMatch(String::isBlank);
        if (isInputContainsBlank) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }
    }
}
