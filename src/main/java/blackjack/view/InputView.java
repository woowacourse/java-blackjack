package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String EMPTY_INPUT_ERROR_MESSAGE = "빈 문자는 입력할 수 없습니다.";
    private static final String NOT_Y_OR_N_ERROR_MESSAGE = "y 또는 n 으로만 입력해주세요.";
    private static final String YES_MESSAGE = "y";
    private static final String NO_MESSAGE = "n";
    private static final String NAME_DELIMITER = ",";

    public static List<String> requestPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        validateEmpty(input);
        return Arrays.asList(input.split(NAME_DELIMITER, -1));
    }

    private static void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT_ERROR_MESSAGE);
        }
    }

    public static boolean requestCardAddition(String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        try {
            validateYorN(input);
            return input.equals(YES_MESSAGE);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestCardAddition(name);
        }
    }

    private static void validateYorN(String input) {
        if (isNotYorN(input)) {
            throw new IllegalArgumentException(NOT_Y_OR_N_ERROR_MESSAGE);
        }
    }

    private static boolean isNotYorN(String input) {
        return !input.equals(YES_MESSAGE) && !input.equals(NO_MESSAGE);
    }
}
