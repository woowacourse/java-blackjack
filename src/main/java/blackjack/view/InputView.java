package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String HIT_VALUE_ERROR_MESSAGE = "Y 또는 N을 입력해 주세요.";
    private static final String COMMA = ",";

    private InputView() {
    }

    public static List<String> inputPlayers() {
        return Arrays.asList(scanner.nextLine().split(COMMA));
    }

    public static String getHitValue() {
        String value = scanner.nextLine().toUpperCase();
        validateHitValue(value);
        return value;
    }

    private static void validateHitValue(String value) {
        List<String> hitValues = Arrays.asList("Y", "N");
        if (!hitValues.contains(value)) {
            throw new IllegalArgumentException(HIT_VALUE_ERROR_MESSAGE);
        }
    }
}
