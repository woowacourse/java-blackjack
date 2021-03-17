package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMA = ",";
    private static final String WITH_BLANK = "\\s+";
    private static final String NO_BLANK = "";

    private InputView() {
    }

    public static List<String> inputNames() {
        String input = deleteWhiteSpaces(SCANNER.nextLine());
        return Arrays.asList(input.split(COMMA));
    }

    private static String deleteWhiteSpaces(String input) {
        return input.replaceAll(WITH_BLANK, NO_BLANK);
    }

    public static String inputString() {
        return SCANNER.nextLine();
    }

    public static long inputLong() {
        try {
            return Long.parseLong(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 정수여야 합니다.");
        }
    }
}
