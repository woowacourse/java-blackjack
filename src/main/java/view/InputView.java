package view;

import view.util.InputParser;
import view.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String HIT_COMMAND = "y";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputName() {
        String input = readLine();
        InputValidator.validateInput(input);
        return InputParser.separateBySeparator(input);
    }

    public static boolean inputHitOrStand() {
        String input = readLine();
        InputValidator.validateChoiceInput(input);
        return HIT_COMMAND.equals(input);
    }

    public static int inputBettingMoney() {
        String input = readLine();
        InputValidator.validateMoneyInput(input);
        return InputParser.parseInt(input);
    }

    private static String readLine() {
        return SCANNER.nextLine();
    }
}
