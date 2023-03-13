package blackjack.view;

import blackjack.view.exception.InvalidChoiceException;
import blackjack.view.exception.NotIntegerException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String REQUEST_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String REQUEST_BETTING_MONEY = "의 배팅 금액은?";
    private static final String REQUEST_PLAYER_CHOICE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    public static List<String> inputPlayerNames() {
        System.out.println(REQUEST_PLAYER_NAME);

        String playerNames = scanner.nextLine();
        return splitInputByDelimiter(playerNames);
    }

    private static List<String> splitInputByDelimiter(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    public static int inputMoney(final String playerName) {
        System.out.println();
        System.out.println(playerName + REQUEST_BETTING_MONEY);
        String input = scanner.nextLine();
        validateInteger(input);
        return Integer.parseInt(input);
    }

    private static void validateInteger(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotIntegerException();
        }
    }

    public static boolean inputPlayerChoice(final String name) {
        System.out.println();
        System.out.println(name + REQUEST_PLAYER_CHOICE);
        String choice = scanner.nextLine();
        validateChoice(choice);
        return getChoice(choice);
    }

    private static void validateChoice(final String choice) {
        if (!choice.equalsIgnoreCase(YES) && !choice.equalsIgnoreCase(NO)) {
            throw new InvalidChoiceException();
        }
    }

    private static boolean getChoice(String choice) {
        return choice.equalsIgnoreCase(YES);
    }

    public static void terminate() {
        scanner.close();
    }
}
