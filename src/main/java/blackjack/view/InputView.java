package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String INVALID_MONEY_VALUE = "돈은 숫자이어야 합니다.";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    private InputView() {
    }

    public static int inputMoney() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(INVALID_MONEY_VALUE);
        }
    }

    public static List<String> inputPlayers() {
        return Arrays.asList(scanner.nextLine().split(DELIMITER));
    }

    public static String inputHitValue() {
        return scanner.nextLine().toUpperCase();
    }
}
