package view;

import java.util.List;
import java.util.Scanner;
import utils.StringUtils;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String USER_YES_OR_NO_INPUT_ERROR_MESSAGE = "y 또는 n 을 입력해야 합니다.";

    public static List<String> readPlayerNames() {
        OutputView.printEnterPlayerNames();
        return StringUtils.splitIntoList(SCANNER.nextLine());
    }

    public static boolean askWantMoreCard(String name) {
        OutputView.printAskWantMoreCard(name);
        String input = SCANNER.nextLine();

        if ("y".equals(input)) {
            return true;
        }
        if ("n".equals(input)) {
            return false;
        }
        throw new UnsupportedOperationException(USER_YES_OR_NO_INPUT_ERROR_MESSAGE);
    }
}
