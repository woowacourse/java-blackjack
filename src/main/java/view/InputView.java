package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String INPUT_EMPTY_ERROR_MESSAGE = "[ERROR] 입력값이 존재하지 않습니다.";
    public static final String SPLITTER = ",";

    public static List<String> inputPlayerNames() {
        String playerNames = SCANNER.nextLine();
        validateBlank(playerNames);
        return List.of(playerNames.split(SPLITTER, -1));
    }

    public static void validateBlank(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(INPUT_EMPTY_ERROR_MESSAGE);
        }
    }

    public static boolean inputReceiveOrNot() {
        return ReceiveValidate.checkReceivable(SCANNER.nextLine());
    }
}
