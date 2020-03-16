package view;

import java.util.List;
import java.util.Scanner;
import utils.StringUtils;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

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
        throw new IllegalUserInputException("y 또는 n 을 입력해야 합니다.");
    }
}
