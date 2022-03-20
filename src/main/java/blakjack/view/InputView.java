package blakjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NAME_DELIMITER = ",";
    private static final String DUPLICATE_NAME_MESSAGE = "중복된 이름은 사용할 수 없습니다.";
    private static final String BLANK_INPUT_MESSAGE = "값을 입력해주세요.";

    private InputView() {
    }

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_NAME_MESSAGE);
        final String rawInput = readLine();
        return checkDuplication(parseToNames(rawInput));
    }

    private static String readLine() {
        String input = SCANNER.nextLine();
        if (input.isBlank()) {
            System.out.println(BLANK_INPUT_MESSAGE);
            return readLine();
        }
        return input;
    }

    private static List<String> parseToNames(final String rawInput) {
        return Arrays.stream(rawInput.split(NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static List<String> checkDuplication(final List<String> names) {
        final var before = names.size();
        final var after = (int) names.stream()
                .distinct()
                .count();
        if (before == after) {
            return names;
        }
        System.out.println(DUPLICATE_NAME_MESSAGE);
        return inputPlayerNames();
    }
}
