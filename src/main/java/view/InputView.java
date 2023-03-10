package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String SPLITTER = ",";

    public static List<String> inputPlayerNames() {
        String playerNames = SCANNER.nextLine();
        InputValidator.validateBlank(playerNames);
        return List.of(playerNames.split(SPLITTER, -1));
    }

    public static String inputReceiveOrNot() {
        String receiveOrNot = SCANNER.nextLine();
        InputValidator.validateReceiveOrNot(receiveOrNot);
        return receiveOrNot;
    }

    public static String inputBettingMoney() {
        return SCANNER.nextLine();
    }
}
