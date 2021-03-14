package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String GAMER_NAME_MESSAGE = "게임에 참여알 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_DRAW_FORMAT =
        "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니요는 n)" + System.lineSeparator();
    private static final String REGEX = ",";
    private static final String BETTING_MESSAGE = "%s의 배팅 금액은?" + System.lineSeparator();

    public static List<String> getGamerNamesFromUser() {
        OutputView.printMessage(GAMER_NAME_MESSAGE);
        return Arrays.stream(SCANNER.nextLine().split(REGEX))
            .map(String::trim)
            .collect(toList());
    }

    public static boolean getYesOrNo(String name) {
        try {
            System.out.printf(ASK_DRAW_FORMAT, name);

            String input = SCANNER.nextLine();
            validateYesOrNo(input);

            return yesOrNoToBoolean(input);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return getYesOrNo(name);
        }
    }

    private static void validateYesOrNo(String input) {
        if (!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n"))) {
            throw new IllegalArgumentException("y 또는 n 을 입력해주세요.");
        }
    }

    private static boolean yesOrNoToBoolean(String input) {
        return input.equals("y");
    }

    public static int getBettingMoneyFromUser(String player) {
        System.out.printf(BETTING_MESSAGE, player);

        String input = SCANNER.nextLine();
        isNumeric(input);

        return Integer.parseInt(input);
    }

    private static void isNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("배팅 금액은 숫자여야 합니다.");
        }
    }
}
