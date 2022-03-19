package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String EMPTY_INPUT_ERROR_MESSAGE = "빈 문자는 입력할 수 없습니다.";
    private static final String NOT_NUMBER_ERROR_MESSAGE = "숫자만 입력해주세요.";
    private static final String NOT_HIT_OR_STAND_ERROR_MESSAGE = "y 또는 n 으로만 입력해주세요.";
    private static final String HIT = "y";
    private static final String STAND = "n";
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

    public static int requestBettingMoney(String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
        String input = scanner.nextLine();
        try {
            validateEmpty(input);
            validateInteger(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestBettingMoney(playerName);
        }
    }

    private static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    public static boolean requestHit(String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        try {
            validateHitOrStay(input);
            return input.equals(HIT);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestHit(name);
        }
    }

    private static void validateHitOrStay(String input) {
        if (isNotHitOrStay(input)) {
            throw new IllegalArgumentException(NOT_HIT_OR_STAND_ERROR_MESSAGE);
        }
    }

    private static boolean isNotHitOrStay(String input) {
        return !input.equals(HIT) && !input.equals(STAND);
    }
}
