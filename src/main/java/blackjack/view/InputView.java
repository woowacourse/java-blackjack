package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String NATURAL_NUMBER_REGEX = "^[1-9]+[0-9]*$";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return validateInput(scanner.nextLine());
    }

    public static String readGameCommandToGetOneMoreCard(final String playerName) {
        System.out.println(NEW_LINE + playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return validateInput(scanner.nextLine());
    }

    public static int readBettingMoney(String playerName) {
        System.out.println(NEW_LINE + playerName + "의 배팅 금액은?");
        String input = validateInput(scanner.nextLine());
        validateNaturalNumber(input);
        return Integer.parseInt(input);
    }

    private static String validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("공백은 입력이 불가능합니다.");
        }
        return input;
    }

    private static void validateNaturalNumber(String input) {
        if (!input.matches(NATURAL_NUMBER_REGEX)) {
            throw new IllegalArgumentException("자연수를 입력해주세요.");
        }
    }
}
