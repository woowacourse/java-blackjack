package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NAME_OR_CARD_DELIMITER = ",";
    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DUPLICATE_NAME_MESSAGE = "중복된 이름은 불가합니다.";
    private static final String BLANK_INPUT_MESSAGE = "값을 입력해주세요.";

    private InputView() {
    }

    public static List<String> inputPlayerName() {
        print(INPUT_NAME_MESSAGE);
        final String names = readLine();
        if (hasNoDuplication(names)) {
            return getSplitAndTrim(names);
        }
        print(DUPLICATE_NAME_MESSAGE);
        return inputPlayerName();
    }

    private static void print(final String message) {
        System.out.println(message);
    }

    private static String readLine() {
        String input = SCANNER.nextLine();
        if (input.isBlank()) {
            print(BLANK_INPUT_MESSAGE);
            return readLine();
        }
        return input;
    }

    static boolean hasNoDuplication(final String input) {
        final List<String> parsedInput = getSplitAndTrim(input);
        final int beforeCount = parsedInput.size();
        final int afterCount = (int) parsedInput.stream().distinct().count();
        return beforeCount == afterCount;
    }

    private static List<String> getSplitAndTrim(final String input) {
        return Arrays.stream(input.split(NAME_OR_CARD_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
