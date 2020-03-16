package view;

import domain.PlayerAnswer;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NAMES_DELIMITER = ",";

    public static String[] requestName() {
        OutputView.printNameFormat();
        return scanner.nextLine().split(NAMES_DELIMITER);
    }

    public static PlayerAnswer requestDraw() {
        return new PlayerAnswer(scanner.nextLine());
    }
}
