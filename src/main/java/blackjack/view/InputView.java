package blackjack.view;

import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_USERNAME_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_MORE_CARD_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + lineSeparator();
    private static final String INPUT_INVALID_ANSWER = "[ERROR] y 또는 n만 입력 가능합니다.";
    private static final String AGREE = "y";
    private static final String DISAGREE = "n";
    private static final String REGEX = ", |,";
    private static final String ERROR_INVALID_INTEGER = "[ERROR] 정수만 입력 가능합니다.";


    public static String[] inputUsersName() {
        System.out.println(INPUT_USERNAME_GUIDE);
        return scanner.nextLine().split(REGEX);
    }

    public static boolean inputMoreCard(String userName) {
        System.out.printf(INPUT_MORE_CARD_FORMAT, userName);
        return isContinue(scanner.nextLine());
    }

    private static boolean isContinue(String userInput) {
        if (userInput.equals(AGREE)) {
            return true;
        }
        if (userInput.equals(DISAGREE)) {
            return false;
        }
        throw new IllegalArgumentException(INPUT_INVALID_ANSWER);
    }

    public static int getUserPrice(String userName) {
        System.out.println(userName + "의 베팅 금액은?");
        String value = scanner.nextLine();
        validateInteger(value);
        return Integer.parseInt(value);
    }

    private static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_INTEGER);
        }
    }

}
