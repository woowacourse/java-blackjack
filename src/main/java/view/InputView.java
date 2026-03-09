package view;

import view.util.InputParser;
import view.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String HIT_COMMAND = "y";

    public static List<String> inputName() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        InputValidator.validateInput(input);
        return InputParser.separateBySeparator(input);
    }

    public static boolean inputHitOrStand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        InputValidator.validateChoiceInput(input);
        return HIT_COMMAND.equals(input);
    }
}
