package blackjack.view;

import blackjack.exception.InvalidChoiceException;
import blackjack.exception.NotIntegerException;
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
    private static final String REQUEST_PLAYER_CHOICE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String REQUEST_PLAYER_BET_MONEY = "의 베팅 금액은?";

    public static List<String> inputPlayerNames() {
        System.out.println(REQUEST_PLAYER_NAME);

        String playerNames = scanner.nextLine();
        return splitInputByDelimiter(playerNames);
    }

    private static List<String> splitInputByDelimiter(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static boolean inputPlayerChoice(String name) {
        System.out.println();
        System.out.println(name + REQUEST_PLAYER_CHOICE);
        String choice = scanner.nextLine();
        return validateChoice(choice);
    }

    private static boolean validateChoice(String choice) {
        String lowerCase = choice.toLowerCase();
        if (lowerCase.equals(YES)) {
            return true;
        }
        if (lowerCase.equals(NO)) {
            return false;
        }
        throw new InvalidChoiceException();
    }

    public static int inputPlayerBetMoney(String name) {
        System.out.println(name + REQUEST_PLAYER_BET_MONEY);
        String betMoney = scanner.nextLine();
        return validateBetMoney(betMoney);
    }

    private static int validateBetMoney(String betMoney) {
        try {
            return Integer.parseInt(betMoney);
        } catch (IllegalArgumentException e) {
            throw new NotIntegerException();
        }
    }
}
