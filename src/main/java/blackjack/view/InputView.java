package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public static final String WRONG_INPUT_ERROR_MESSAGE = "유효한 대답이 아닙니다.";
    public static final String BETTING_MONEY_IS_NUMBER_ERROR_MESSAGE = "숫자를 입력해야합니다.";
    public static final String OK = "y";
    private static final String NO = "n";
    private static final String DELIMITER = ",";
    private static final String BLANK = " ";
    private static final String EMPTY = "";
    private static final String INTEGER_PATTERN = "-?\\d+";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputName() {
        String allPlayersName = scanner.nextLine();
        return Arrays.stream(allPlayersName.split(DELIMITER))
                .map(s -> s.replaceAll(BLANK, EMPTY))
                .collect(Collectors.toList());
    }

    public static boolean inputAnswer() {
        String answer = scanner.nextLine();
        if (!answer.equals(OK) && !answer.equals(NO)) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR_MESSAGE);
        }
        return answer.equals(OK);
    }

    public static String inputBettingMoney() {
        String money = scanner.nextLine();
        if (!money.matches(INTEGER_PATTERN)) {
            throw new IllegalArgumentException(BETTING_MONEY_IS_NUMBER_ERROR_MESSAGE);
        }
        return money;
    }
}
