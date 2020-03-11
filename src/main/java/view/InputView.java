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
}
