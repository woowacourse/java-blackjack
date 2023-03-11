package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String BLANK_INPUT_INVALID_ERROR_MESSAGE = "빈 값은 허용되지 않습니다.";
    private static final String DELIMITER = ",";
    private static final String INPUT_NAMES_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_BET_REQUEST_MESSAGE = "의 베팅 금액은?";
    private static final String INPUT_CARD_INTENT_REQUEST_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String INPUT_CHECK_LETTER_ERROR_MESSAGE = "허용되지 않는 입력입니다.";
    private static final String NOT_0_OR_POSITIVE_INVALID_ERROR_MESSAGE = "0 이상의 수만 입력 가능합니다.";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String NEW_LINE = "\n";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputNames() {
        System.out.println(INPUT_NAMES_REQUEST_MESSAGE);
        String input = SCANNER.nextLine().replace(" ", "");
        checkBlank(input);
        return Arrays.stream(input.split(DELIMITER))
            .collect(Collectors.toList());
    }

    private static void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(BLANK_INPUT_INVALID_ERROR_MESSAGE);
        }
    }

    public static int inputBet(String name) {
        System.out.println(NEW_LINE + name + INPUT_BET_REQUEST_MESSAGE);
        String input = SCANNER.nextLine();
        checkPositive(input);
        return Integer.parseInt(input);
    }

    private static void checkPositive(String input) {
        if (!input.matches("^[0-9]*$")) {
            throw new IllegalArgumentException(NOT_0_OR_POSITIVE_INVALID_ERROR_MESSAGE);
        }
    }

   public static boolean inputPlayerHitOrStand(final NameCardScoreDto nameCardScore) {
        System.out.printf((INPUT_CARD_INTENT_REQUEST_MESSAGE) + NEW_LINE, nameCardScore.getName());
        String input = SCANNER.nextLine();
        checkLetter(input, YES, NO);
        return input.equals(YES);
    }

    private static void checkLetter(final String input, final String... expectedLetters) {
        Arrays.stream(expectedLetters)
            .filter(input::equals)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(INPUT_CHECK_LETTER_ERROR_MESSAGE));
    }
}
