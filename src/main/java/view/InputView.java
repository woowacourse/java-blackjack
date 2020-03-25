package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NAMES_DELIMITER = ",";

    public static String[] requestName() {
        OutputView.printNameFormat();
        return scanner.nextLine().split(NAMES_DELIMITER);
    }

    public static String requestBettingMoney(String name) {
        OutputView.printBettingMoneyFormat(name);
        return scanner.nextLine();
    }

    public static String requestDraw() {
        return scanner.nextLine();
    }
}
